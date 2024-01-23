package assignment3;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;


public class Block {
 private int xCoord;
 private int yCoord;
 private int size; // height/width of the square
 private int level; // the root (outer most block) is at level 0
 private int maxDepth; 
 private Color color;

 private Block[] children; // {UR, UL, LL, LR}
 public static Random gen = new Random(4);

 
 /*
  * These two constructors are here for testing purposes. 
  */
 public Block() {}
 
 public Block(int x, int y, int size, int lvl, int  maxD, Color c, Block[] subBlocks) {
  this.xCoord=x;
  this.yCoord=y;
  this.size=size;
  this.level=lvl;
  this.maxDepth = maxD;
  this.color=c;
  this.children = subBlocks;
 }


 /*
  * Creates a random block given its level and a max depth. 
  * 
  * xCoord, yCoord, size, and highlighted should not be initialized
  * (i.e. they will all be initialized by default)
  */
 public Block(int lvl, int maxDepth) {
  this.level = lvl;
  this.maxDepth = maxDepth;
  if (lvl > maxDepth){
   throw new IllegalArgumentException("The input level cannot be greater than the maxDepth");
  }
  if (lvl < maxDepth) {
   double randomNumber = gen.nextDouble();
   if (randomNumber < Math.exp(-0.25 * lvl)) {
    this.color = null;
    this.children = new Block[4];
    this.children[0] = new Block(lvl + 1, maxDepth);
    this.children[1] = new Block(lvl + 1, maxDepth);
    this.children[2] = new Block(lvl + 1, maxDepth);
    this.children[3] = new Block(lvl + 1, maxDepth);
    return;
  }else {
     this.children = new Block[0];
     double newRandomNumber = gen.nextInt(4);
     if (newRandomNumber == 0) {
      this.color = GameColors.RED;
     }
     if (newRandomNumber == 1) {
      this.color = GameColors.GREEN;
     }
     if (newRandomNumber == 2) {
      this.color = GameColors.BLUE;
     }
     if (newRandomNumber == 3) {
      this.color = GameColors.YELLOW;
     }
   }
  }else {
   this.children = new Block[0];
   double newRandomNumber = gen.nextInt(4);
   if (newRandomNumber == 0) {
    this.color = GameColors.RED;
   }
   if (newRandomNumber == 1) {
    this.color = GameColors.GREEN;
   }
   if (newRandomNumber == 2) {
    this.color = GameColors.BLUE;
   }
   if (newRandomNumber == 3) {
    this.color = GameColors.YELLOW;
   }
 }}




 /*
  * Updates size and position for the block and all of its sub-blocks, while
  * ensuring consistency between the attributes and the relationship of the 
  * blocks. 
  * 
  *  The size is the height and width of the block. (xCoord, yCoord) are the 
  *  coordinates of the top left corner of the block. 
  */
 public void updateSizeAndPosition (int size, int xCoord, int yCoord) {
  if (size<=0 || ((size%2 != 0) &&  (this.maxDepth-this.level) != 0 )){
   throw new IllegalArgumentException("Invalid inputs");
  }

  if (this.children.length != 0 && this.level<(this.maxDepth)) {
   this.xCoord = xCoord;
   this.yCoord = yCoord;
   this.size = size;
   size = size/2;
    children[0].updateSizeAndPosition(size, size+xCoord, yCoord);
   children[0].size = size;
    children[1].updateSizeAndPosition(size, xCoord, yCoord );
   children[1].size = size;
    children[2].updateSizeAndPosition(size, xCoord, size+yCoord);
   children[2].size = size;
    children[3].updateSizeAndPosition(size, size+xCoord, size+yCoord);
   children[3].size = size;
   return;
  }else{
   this.xCoord = xCoord ;
   this.yCoord = yCoord;
  }}

 /*
  * Returns a List of blocks to be drawn to get a graphical representation of this block.
  * 
  * This includes, for each undivided Block:
  * - one BlockToDraw in the color of the block
  * - another one in the FRAME_COLOR and stroke thickness 3
  * 
  * Note that a stroke thickness equal to 0 indicates that the block should be filled with its color.
  *  
  * The order in which the blocks to draw appear in the list does NOT matter.
  */


 public ArrayList<BlockToDraw> getBlocksToDraw() {
  ArrayList<BlockToDraw> blockToDrawArrayList = new ArrayList<>();

  if (this.children.length == 0){
   blockToDrawArrayList.add(new BlockToDraw(this.color,this.xCoord,this.yCoord, this.size,0));
   blockToDrawArrayList.add(new BlockToDraw(GameColors.FRAME_COLOR,this.xCoord,this.yCoord, this.size,3));
  } else {
   for (int i = 0; i < 4; i++) {
    blockToDrawArrayList.addAll(children[i].getBlocksToDraw());
   }
  }
  return blockToDrawArrayList;
 }

 /*
  * This method is provided and you should NOT modify it. 
  */
 public BlockToDraw getHighlightedFrame() {
  return new BlockToDraw(GameColors.HIGHLIGHT_COLOR, this.xCoord, this.yCoord, this.size, 5);
 }
 
 
 
 /*
  * Return the Block within this Block that includes the given location
  * and is at the given level. If the level specified is lower than 
  * the lowest block at the specified location, then return the block 
  * at the location with the closest level value.
  * 
  * The location is specified by its (x, y) coordinates. The lvl indicates 
  * the level of the desired Block. Note that if a Block includes the location
  * (x, y), and that Block is subdivided, then one of its sub-Blocks will 
  * contain the location (x, y) too. This is why we need lvl to identify 
  * which Block should be returned. 
  * 
  * Input validation: 
  * - this.level <= lvl <= maxDepth (if not throw exception)
  * - if (x,y) is not within this Block, return null.
  */
 public Block getSelectedBlock(int x, int y, int lvl) {

  if (lvl > this.maxDepth || lvl < 0 || this.level > lvl) {
   throw new IllegalArgumentException("the level input is higher than the maximum depth of the block");
  }

  if (this.level == lvl || this.children.length == 0) {
   return this;


  } else {
   if (x < (this.xCoord + (this.size / 2))) {
    if(y<this.yCoord+this.size/2) {
     return this.children[1].getSelectedBlock(x, y, lvl);
    }
    else {
     return this.children[2].getSelectedBlock(x,y,lvl);
    }
   } else {
    if(y<this.yCoord+this.size/2) {
     return this.children[0].getSelectedBlock(x,y,lvl);
    }
    else {
     return this.children[3].getSelectedBlock(x,y,lvl);
    }
   }
  }
 }
 
 

 /*
  * Swaps the child Blocks of this Block. 
  * If input is 1, swap vertically. If 0, swap horizontally. 
  * If this Block has no children, do nothing. The swap 
  * should be propagate, effectively implementing a reflection
  * over the x-axis or over the y-axis.
  * 
  */
 public void reflect(int direction) {
  if (direction != 0 && direction != 1){
   throw new IllegalArgumentException("Input integer is not a 0 nor a 1");
  }
  else{
   if (this.children.length != 0) {
    if (direction == 0) {
     Block temp0 = new Block();
     Block temp1 = new Block();
     Block temp2 = new Block();
     Block temp3 = new Block();

     temp0 = this.children[0];
     temp1 = this.children[1];
     temp2 = this.children[2];
     temp3 = this.children[3];

     this.children[0] = temp3;
     this.children[0].xCoord = temp3.xCoord;
     this.children[0].yCoord = temp3.yCoord;
     this.children[0].color = temp3.color;
     this.children[0].size = temp3.size;
     this.children[0].level = temp3.level;

     this.children[1] = temp2;
     this.children[1].xCoord = temp2.xCoord;
     this.children[1].yCoord = temp2.yCoord;
     this.children[1].color = temp2.color;
     this.children[1].size = temp2.size;
     this.children[1].level = temp2.level;

     this.children[2] = temp1;
     this.children[2].xCoord = temp1.xCoord;
     this.children[2].yCoord = temp1.yCoord;
     this.children[2].color = temp1.color;
     this.children[2].size = temp1.size;
     this.children[2].level = temp1.level;

     this.children[3] = temp0;
     this.children[3].xCoord = temp0.xCoord;
     this.children[3].yCoord = temp0.yCoord;
     this.children[3].color = temp0.color;
     this.children[3].size = temp0.size;
     this.children[3].level = temp0.level;
     this.updateSizeAndPosition(this.size,this.xCoord,this.yCoord);

    }
    if (direction == 1) {
     Block temp0 = new Block();
     Block temp1 = new Block();
     Block temp2 = new Block();
     Block temp3 = new Block();

     temp0 = this.children[0];
     temp1 = this.children[1];
     temp2 = this.children[2];
     temp3 = this.children[3];

     this.children[0] = temp1;
     this.children[0].xCoord = temp1.xCoord;
     this.children[0].yCoord = temp1.yCoord;
     this.children[0].color = temp1.color;
     this.children[0].size = temp1.size;
     this.children[0].level = temp1.level;

     this.children[1] = temp0;
     this.children[1].xCoord = temp0.xCoord;
     this.children[1].yCoord = temp0.yCoord;
     this.children[1].color = temp0.color;
     this.children[1].size = temp0.size;
     this.children[1].level = temp0.level;

     this.children[2] = temp3;
     this.children[2].xCoord = temp3.xCoord;
     this.children[2].yCoord = temp3.yCoord;
     this.children[2].color = temp3.color;
     this.children[2].size = temp3.size;
     this.children[2].level = temp3.level;

     this.children[3] = temp2;
     this.children[3].xCoord = temp2.xCoord;
     this.children[3].yCoord = temp2.yCoord;
     this.children[3].color = temp2.color;
     this.children[3].size = temp2.size;
     this.children[3].level = temp2.level;
     this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);

    }
   }
   else {
    if (this.children.length == 0) {
     return;
    }

   }
   }
  }



 

 
 /*
  * Rotate this Block and all its descendants. 
  * If the input is 1, rotate clockwise. If 0, rotate 
  * counterclockwise. If this Block has no children, do nothing.
  */
 public void rotate(int direction) {
  if (direction != 0 && direction != 1){
   throw new IllegalArgumentException("Input integer is not a 0 nor a 1");
  }
  else{
   if (this.children.length != 0) {
    if (direction == 1) {
     Block temp0 = new Block();
     Block temp1 = new Block();
     Block temp2 = new Block();
     Block temp3 = new Block();

     temp0 = this.children[0];
     temp1 = this.children[1];
     temp2 = this.children[2];
     temp3 = this.children[3];

     this.children[0] = temp1;
     this.children[0].xCoord = temp1.xCoord;
     this.children[0].yCoord = temp1.yCoord;
     this.children[0].color = temp1.color;
     this.children[0].size = temp1.size;
     this.children[0].level = temp1.level;


     this.children[1] = temp2;
     this.children[1].xCoord = temp2.xCoord;
     this.children[1].yCoord = temp2.yCoord;
     this.children[1].color = temp2.color;
     this.children[1].size = temp2.size;
     this.children[1].level = temp2.level;


     this.children[2] = temp3;
     this.children[2].xCoord = temp3.xCoord;
     this.children[2].yCoord = temp3.yCoord;
     this.children[2].color = temp3.color;
     this.children[2].size = temp3.size;
     this.children[2].level = temp3.level;



     this.children[3] = temp0;
     this.children[3].xCoord = temp0.xCoord;
     this.children[3].yCoord = temp0.yCoord;
     this.children[3].color = temp0.color;
     this.children[3].size = temp0.size;
     this.children[3].level = temp0.level;
     this.updateSizeAndPosition(this.size,this.xCoord,this.yCoord);


    }
    if (direction == 0) {
     Block temp0 = new Block();
     Block temp1 = new Block();
     Block temp2 = new Block();
     Block temp3 = new Block();

     temp0 = this.children[0];
     temp1 = this.children[1];
     temp2 = this.children[2];
     temp3 = this.children[3];

     this.children[0] = temp3;
     this.children[0].xCoord = temp3.xCoord;
     this.children[0].yCoord = temp3.yCoord;
     this.children[0].color = temp3.color;
     this.children[0].size = temp3.size;
     this.children[0].level = temp3.level;

     this.children[1] = temp0;
     this.children[1].xCoord = temp0.xCoord;
     this.children[1].yCoord = temp0.yCoord;
     this.children[1].color = temp0.color;
     this.children[1].size = temp0.size;
     this.children[1].level = temp0.level;

     this.children[2] = temp1;
     this.children[2].xCoord = temp1.xCoord;
     this.children[2].yCoord = temp1.yCoord;
     this.children[2].color = temp1.color;
     this.children[2].size = temp1.size;
     this.children[2].level = temp1.level;

     this.children[3] = temp2;
     this.children[3].xCoord = temp2.xCoord;
     this.children[3].yCoord = temp2.yCoord;
     this.children[3].color = temp2.color;
     this.children[3].size = temp2.size;
     this.children[3].level = temp2.level;

     this.updateSizeAndPosition(this.size,this.xCoord,this.yCoord);


    }
   }
   else{
    if (this.children.length == 0){return;}
 }}}




 /*
  * Smash this Block.
  * 
  * If this Block can be smashed,
  * randomly generate four new children Blocks for it.  
  * (If it already had children Blocks, discard them.)
  * Ensure that the invariants of the Blocks remain satisfied.
  * 
  * A Block can be smashed iff it is not the top-level Block 
  * and it is not already at the level of the maximum depth.
  * 
  * Return True if this Block was smashed and False otherwise.
  * 
  */
 public boolean smash() {
  int lvl = this.level;
  if (this.level != 0  && lvl<this.maxDepth){
   this.children = new Block[4];
   this.children[0] = new Block(lvl+1, this.maxDepth);
   this.children[1] = new Block(lvl+1, this.maxDepth);
   this.children[2] = new Block(lvl+1, this.maxDepth);
   this.children[3] = new Block(lvl+1, this.maxDepth);

   this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
  return true;
 }
  else{
   return false;
  }
  }
 
 
 /*
  * Return a two-dimensional array representing this Block as rows and columns of unit cells.
  * 
  * Return and array arr where, arr[i] represents the unit cells in row i, 
  * arr[i][j] is the color of unit cell in row i and column j.
  * 
  * arr[0][0] is the color of the unit cell in the upper left corner of this Block.
  */

 public Color[][] flatten() {
  int len = (int) Math.pow(2, this.maxDepth - this.level);
  Color[][] arr = new Color[len][len];

   if (this.children.length==0) {
    for (int i = 0; i < arr.length; i++) {
     for (int j = 0; j < arr.length; j++) {
      arr[i][j] = this.color;
     }
    }
   } else {

    Color[][] upperLeft = this.children[1].flatten();
    Color[][] upperRight = this.children[0].flatten();
    Color[][] bottomLeft = this.children[2].flatten();
    Color[][] bottomRight = this.children[3].flatten();

    for (int x = 0; x < len / 2; x++) {
     for (int y = 0; y < len / 2; y++) {
      arr[x][y] = upperLeft[x][y];
     }
    }

    for (int x = 0; x < len / 2; x++) {
     for (int y =len / 2; y < len; y++) {
      arr[x][y] = upperRight[x][y - (len / 2)];
     }
    }

    for (int x = (len) / 2; x < len; x++) {
     for (int y = 0; y < len / 2; y++) {
      arr[x][y] = bottomLeft[x - (len / 2)][y];
     }
    }

    for (int x = (len/ 2); x < (len); x++) {
     for (int y = (len) / 2; y < (len); y++) {
      arr[x][y] = bottomRight[x - (len/2)][y - (len/2)];
     }
    }
   }
   return arr;
 }




 // These two get methods have been provided. Do NOT modify them. 
 public int getMaxDepth() {
  return this.maxDepth;
 }
 
 public int getLevel() {
  return this.level;
 }


 /*
  * The next 5 methods are needed to get a text representation of a block. 
  * You can use them for debugging. You can modify these methods if you wish.
  */
 public String toString() {
  return String.format("pos=(%d,%d), size=%d, level=%d"
    , this.xCoord, this.yCoord, this.size, this.level);
 }

 public void printBlock() {
  this.printBlockIndented(0);
 }

 private void printBlockIndented(int indentation) {
  String indent = "";
  for (int i=0; i<indentation; i++) {
   indent += "\t";
  }

  if (this.children.length == 0) {
   // it's a leaf. Print the color!
   String colorInfo = GameColors.colorToString(this.color) + ", ";
   System.out.println(indent + colorInfo + this);   
  } else {
   System.out.println(indent + this);
   for (Block b : this.children)
    b.printBlockIndented(indentation + 1);
  }
 }
 
 private static void coloredPrint(String message, Color color) {
  System.out.print(GameColors.colorToANSIColor(color));
  System.out.print(message);
  System.out.print(GameColors.colorToANSIColor(Color.WHITE));
 }

 public void printColoredBlock(){
  Color[][] colorArray = this.flatten();
  for (Color[] colors : colorArray) {
   for (Color value : colors) {
    String colorName = GameColors.colorToString(value).toUpperCase();
    if(colorName.length() == 0){
     colorName = "\u2588";
    }else{
     colorName = colorName.substring(0, 1);
    }
    coloredPrint(colorName, value);
   }
   System.out.println();
  }
 }






 /*public static void main(String[] args){
  Block blockDepth2 = new Block(0,2);
  blockDepth2.updateSizeAndPosition(16,0,0);
  blockDepth2.printColoredBlock();

  Block blockDepth3 =  new Block(0,3);
  blockDepth3.updateSizeAndPosition(16,0,0);
  Block b1 = blockDepth3.getSelectedBlock(3,5,2);
  b1.printBlock();}*/
 }








