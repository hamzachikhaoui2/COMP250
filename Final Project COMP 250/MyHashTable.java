package finalproject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;




public class MyHashTable<K,V> implements Iterable<MyPair<K,V>>{
	// num of entries to the table
	private int size;
	// num of buckets 
	private int capacity = 16;
	// load factor needed to check for rehashing 
	private static final double MAX_LOAD_FACTOR = 0.75;
	// ArrayList of buckets. Each bucket is a LinkedList of HashPair
	private ArrayList<LinkedList<MyPair<K,V>>> buckets;


	// constructors
	public MyHashTable() {
		// ADD YOUR CODE BELOW THIS
		this.size = 0;
		this.capacity= 16;//I don't think this line does anything important
		this.buckets = new ArrayList<LinkedList<MyPair<K,V>>>();

		for (int i = 0 ; i < this.capacity; i++){
			this.buckets.add(new LinkedList<MyPair<K,V>>());
		}
		//ADD YOUR CODE ABOVE THIS
	}

	public MyHashTable(int initialCapacity) {
		// ADD YOUR CODE BELOW THIS
		this.capacity = Math.abs(initialCapacity);
		this.size = 0;
		this.buckets = new ArrayList<LinkedList<MyPair<K,V>>>();


		for (int i = 0 ; i < initialCapacity; i++){
			this.buckets.add(new LinkedList<MyPair<K,V>>());
		}

		//ADD YOUR CODE ABOVE THIS
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public int numBuckets() {
		return this.capacity;
	}

	/**
	 * Returns the buckets variable. Useful for testing  purposes.
	 */
	public ArrayList<LinkedList< MyPair<K,V> > > getBuckets(){
		return this.buckets;
	}

	/**
	 * Given a key, return the bucket position for the key. 
	 */
	public int hashFunction(K key) {
		int hashValue = Math.abs(key.hashCode())%this.capacity;
		return hashValue;
	}

	/**
	 * Takes a key and a value as input and adds the corresponding HashPair
	 * to this HashTable. Expected average run time  O(1)
	 */
	public V put(K key, V value) {
		//  ADD YOUR CODE BELOW HERE

		if (value == null){
			System.out.println("value input is null");
		}

		MyPair<K,V> newPair = new MyPair<>(key, value);
		double loadFactor = ((double)this.size())/((double)this.numBuckets());
		if (loadFactor>MAX_LOAD_FACTOR || (((double)this.size()) + 1)/((double)this.numBuckets()) > MAX_LOAD_FACTOR){
			rehash(); }


		int indexKey = hashFunction(key);

		if (indexKey > this.capacity){
			throw new IllegalArgumentException("The input number is too large. No element exist at this position");
		}

		LinkedList<MyPair<K,V>> listAtIndex= this.buckets.get(indexKey);

		for (MyPair<K,V> myPair : listAtIndex){
			// in the case where thr key is not used (the first non null slot in the LinkedList should contain the newPair
			K keyVal = myPair.getKey();

			if (keyVal.equals(key) && hashFunction(keyVal) == indexKey){
				V tempVal = myPair.getValue();// Not sure this actually changes the value of the KeyValue.
				myPair.setValue(value);
				this.size ++;
				return tempVal;
			}
			}
		listAtIndex.add(newPair);
		this.size ++;
		if (loadFactor>MAX_LOAD_FACTOR){
			rehash(); }
		return null;

		//  ADD YOUR CODE ABOVE HERE
	}


	/**
	 * Get the value corresponding to key. Expected average runtime O(1)
	 */

	public V get(K key) {
		//ADD YOUR CODE BELOW HERE
		int indexKey = hashFunction(key);
		LinkedList<MyPair<K,V>> listAtIndex= this.buckets.get(indexKey);

		for (int i = 0; i<listAtIndex.size();i++){
			K keyVal  = (listAtIndex.get(i)).getKey();
			if (keyVal.equals(key)) {
				V correspondingVal = (listAtIndex.get(i)).getValue();
				return correspondingVal;
			}
		}


		return null;

		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Remove the HashPair corresponding to key . Expected average runtime O(1) 
	 */
	public V remove(K key) {
		//ADD YOUR CODE BELOW HERE
		int indexKey = hashFunction(key);
		LinkedList<MyPair<K,V>> listAtIndex= this.buckets.get(indexKey);

		for (int i = 0; i<listAtIndex.size();i++){
			if (listAtIndex.get(i).getKey().equals(key)){
				V correspondingVal = listAtIndex.get(i).getValue();
				this.size --;
				listAtIndex.remove(i);
				return correspondingVal;}}
		return null;

		//ADD YOUR CODE ABOVE HERE
	}


	/** 
	 * Method to double the size of the hashtable if load factor increases
	 * beyond MAX_LOAD_FACTOR.
	 * Made public for ease of testing.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */
	public void rehash() {
		//ADD YOUR CODE BELOW HERE

		this.capacity = this.capacity*2;
		ArrayList<LinkedList<MyPair<K,V>>> tempArray = new ArrayList<LinkedList<MyPair<K,V>>>();


		for (int i = 0; i<this.capacity;i++){
			LinkedList<MyPair<K,V>> inBucket = new LinkedList<MyPair<K,V>>();
			tempArray.add(inBucket);
		}

		for (int i = 0; i<(this.capacity/2);i++){
			LinkedList<MyPair<K,V>> oldBucket = this.buckets.get(i);
			K key = null;
			for (MyPair<K,V> oldPairIterated : oldBucket){
				MyPair<K, V> oldPair = oldPairIterated;
				key = oldPair.getKey();
				int hashValue = hashFunction(key);
				LinkedList<MyPair<K,V>> newList = tempArray.get(hashValue);
				newList.add(oldPair);
			}




		}
		this.buckets = tempArray;



		//ADD YOUR CODE ABOVE HERE
	}


	/**
	 * Return a list of all the keys present in this hashtable.
	 * Expected average runtime is O(m), where m is the number of buckets
	 */

	public ArrayList<K> getKeySet() {
		//ADD YOUR CODE BELOW HERE
		ArrayList<K> setKeys = new ArrayList<K>();
		ArrayList<LinkedList<MyPair<K,V>>> pairArray = this.buckets;

		for (LinkedList<MyPair<K,V>> list : pairArray){
			for (MyPair<K,V> myPair : list){
				K newPairKey = myPair.getKey();
				setKeys.add(newPairKey);
			}

		}
		
		return setKeys;

		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * Returns an ArrayList of unique values present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<V> getValueSet() {
		//ADD CODE BELOW HERE

		ArrayList<LinkedList<MyPair<K,V>>> pairArray = this.buckets;
		MyHashTable<K,V> tempTable = new MyHashTable<>(this.capacity);
		ArrayList<V> setValues = new ArrayList<V>();
		//Can we use hashsets?
		for (LinkedList<MyPair<K,V>> list : pairArray){
			for (MyPair<K,V> myPair : list){
				tempTable.put((K) myPair.getValue(), (V) myPair.getKey());

				}
			}
		for (MyPair<K,V> myPair : tempTable){
			setValues.add((V) myPair.getKey());}


		//ADD CODE ABOVE HERE

	return setValues;}


	/**
	 * Returns an ArrayList of all the key-value pairs present in this hashtable.
	 * Expected average runtime is O(m) where m is the number of buckets
	 */
	public ArrayList<MyPair<K, V>> getEntries() {
		//ADD CODE BELOW HERE
		ArrayList<MyPair<K, V>> listEntries = new ArrayList<MyPair<K,V>>();
		for (LinkedList<MyPair<K,V>> list : this.buckets){
			for (MyPair<K,V> myPair : list){
				listEntries.add(myPair);
			}

		//ADD CODE ABOVE HERE
	}
	return listEntries;}

	
	@Override
	public MyHashIterator iterator() {
		return new MyHashIterator();
	}


	private class MyHashIterator implements Iterator<MyPair<K,V>> {
		private MyPair<K,V> MyPair;
		ArrayList<MyPair<K,V>> array = new ArrayList<>();
		Iterator<MyPair<K,V>> iterator = array.iterator();

		//Add some fields?? Or use the already existing ones?
		private MyHashIterator() {
			//ADD YOUR CODE BELOW HERE
			ArrayList<LinkedList<MyPair<K,V>>> buckets  = MyHashTable.this.buckets;
			iterate(buckets);
			iterator = array.iterator();


			//ADD YOUR CODE ABOVE HERE
		}
		private void iterate(ArrayList<LinkedList<MyPair<K,V>>> buckets){
			for (LinkedList<MyPair<K,V>> list : buckets){
				for (MyPair<K,V> myPair : list){
					array.add(myPair);
				}
		}
			return;}

		@Override
		public boolean hasNext() {
			//ADD YOUR CODE BELOW HERE
			return iterator.hasNext();

			//ADD YOUR CODE ABOVE HERE
		}

		@Override
		public MyPair<K,V> next() {
			//ADD YOUR CODE BELOW HERE

			return iterator.next();
			
			//ADD YOUR CODE ABOVE HERE
		}

	}
	
}

