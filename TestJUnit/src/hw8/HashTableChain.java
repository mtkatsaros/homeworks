package hw8;
import java.util.*;

/**
 * HashTable implementation using chaining to tack a pair of key and value pairs.
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class HashTableChain<K, V> implements Map<K, V>  {

    private LinkedList<Entry<K, V>>[] table ;
    private  int numKeys ;
    private static final int CAPACITY = 101 ;
    private static final double LOAD_THRESHOLD = 1.5 ;

    ///////////// ENTRY CLASS ///////////////////////////////////////

    /**
     * Contains key-value pairs for HashTable
     * @param <K> the key
     * @param <V> the value
     */
    private static class Entry<K, V> implements Map.Entry<K, V>{
        private K key ;
        private V value ;

        /**
         * Creates a new key-value pair
         * @param key the key
         * @param value the value
         */
        public Entry(K key, V value) {
            this.key = key ;
            this.value = value ;
        }

        /**
         * Returns the key
         * @return the key
         */
        public K getKey() {
            return key;
        }

        /**
         * Returns the value
         * @return the value
         */
        public V getValue() {
            return value ;
        }

        /**
         * Sets the value
         * @param val the new value
         * @return the old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val ;
            return oldVal ;
        }
        @Override
        public String toString() {
            return  key + "=" + value  ;
        }
        
       

    }

    ////////////// end Entry Class /////////////////////////////////

    ////////////// EntrySet Class //////////////////////////////////

    /**
     * Inner class to implement set view
     */
    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {


        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new SetIterator();
        }

        @Override
        public int size() {
            return numKeys ;
        }
    }

    ////////////// end EntrySet Class //////////////////////////////

    //////////////   SetIterator Class ////////////////////////////

    /**
     * Class that iterates over the table. Index is table location
     * and lastItemReturned is entry
     */
    private class SetIterator implements Iterator<Map.Entry<K, V>> {

        private int index = 0 ;
        private Entry<K,V> lastItemReturned = null;
        private Iterator<Entry<K, V>> iter = null;

        @Override
        public boolean hasNext() {
        	// FILL HERE
        	//if the iterator exists and has a next value return true
        	if(iter != null && iter.hasNext())
        		return true;
        	
        	/*
        	 *if the index is less than the array size of the table,
        	 *While there is no list in the next index
        	 *check to see if the next index is greater than the array
        	 *size of the table. If not, return false.
        	 */
        	if(index < table.length) {
        		while(table[++index] == null) {
        			if(index + 1 >= table.length)
        				return false;
        		}
        		iter = table[index].iterator();
        		return iter.hasNext();
        	}
        	return false;
        }

        @Override
        public Map.Entry<K, V> next() {
        	// FILL HERE
        	if(!iter.hasNext())
        		throw new NoSuchElementException();
        	//iterating to the item after the last returned and returning
        	//the resultant value
        	lastItemReturned = iter.next();
        	return lastItemReturned;
        }

        @Override
        public void remove() {
        	// FILL HERE
        	iter.remove();
        	lastItemReturned = null;
        }
    }

    ////////////// end SetIterator Class ////////////////////////////

    /**
     * Default constructor, sets the table to initial capacity size
     */
    public HashTableChain() {
        table = new LinkedList[CAPACITY];
    }

    // returns number of keys
    @Override
    public int size() {
        // FILL HERE
    	return numKeys;
    }

    // returns boolean if table has no keys
    @Override
    public boolean isEmpty() {
    	// FILL HERE
    	return (numKeys == 0);
    }

    // returns boolean if table has the searched for key
    @Override
    public boolean containsKey(Object key) {
    	// Fill Here
    	Iterator<Map.Entry<K, V>> sets = new SetIterator();
    	
    	//while there is a next value in the iterator iterate
    	//a temporary map. if the key is the same as the 
    	//argument's key, return true. If this never 
    	//happens return false.
    	while(sets.hasNext()) {
    		Map.Entry<K,V> temp = sets.next();
    		if(temp.getKey().equals(key)) 
    			return true;	
    	}
    	return false;
    }

    // returns boolean if table has the searched for value
    @Override
    public boolean containsValue(Object value) {
    	// FILL HERE
    	
    	
    	//loop through each value. if any of them have a value return true
    	boolean flag = false;
    	for(int i = 0; i < table.length; i++) {
    		for(int j = 0; j < table[i].size(); j++) 
    			if(table[i].get(j).getValue().equals(value)) flag = true;
    	}
    	return flag;
    }

    // returns Value if table has the searched for key
    @Override
    public V get(Object key) {
    	// FILL HERE
    	//decide index using hashcode and modulus of the table
    	int index = key.hashCode() % table.length;
    	//if the resultant value is negative add the length of the table
    	if(index < 0)
    		index += table.length;
    	//if there is no values stored in this index the value is null
    	if(table[index] == null)
    		return null;
    	
    	//now check every item in the list of that index and return true if found
    	for(Entry<K,V> nextItem : table[index]) {
    		if(nextItem.key.equals(key))
    			return nextItem.value;
    	}
    	return null;
    }

    // adds the key and value pair to the table using hashing
    @Override
    public V put(K key, V value) {
    	// FILL HERE
    	//same index logic as before
    	int index = key.hashCode() % table.length;
    	if(index < 0)
    		index += table.length;
    	
    	//if there is no list inside put one in
    	if(table[index] == null) 
    		table[index] = new LinkedList<Entry<K,V>>();
    	
    	//for each item in the list look for the matching key.
    	//If there is a matching key change the value
    	for(Entry<K,V> nextItem : table[index]) {
    		if(nextItem.key.equals(key)) {
    			V oldVal = nextItem.value;
    			nextItem.setValue((value));
    			return oldVal;
    		}
    	}
    	
    	//add the first item to the linked list since all 
    	//the other scenarios have been met
    	table[index].addFirst(new Entry<K,V>(key, value));
    	numKeys++;
    	//rehash if larger than load threshold
    	if(numKeys > (LOAD_THRESHOLD * table.length))
    		rehash();
    	//return null since there is nothing left
    	return null;
    	
    }


    /**
     * Resizes the table to be 2X +1 bigger than previous
     */
    private void rehash() {
    	// FILL HERE
    	numKeys = 0;
    	//create new table that references old
    	LinkedList<HashTableChain.Entry<K,V>>[] temp = table;
    	//new table that is 2x+1 of size
    	table = new LinkedList[2 * temp.length + 1];
    	
    	//transfer contents to the old list
    	for(int i = 0; i < temp.length; i++) {
    		if(table[i] != null){
    			for(int j = 0; j < table[i].size(); j++) {
    				put(temp[i].get(j).key, temp[i].get(j).value);
    			}
    		}
    	}
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder() ;
        for (int i = 0 ; i < table.length ; i++ ) {
            if (table[i] != null) {
                for (Entry<K, V> nextItem : table[i]) {
                    sb.append(nextItem.toString() + " ") ;
                }
                sb.append(" ");
            }
        }
        return sb.toString() ;

    }

    // remove an entry at the key location
    // return removed value
    @Override
    public V remove(Object key) {
    	// FILL HERE
    	int index = key.hashCode() % table.length;
    	V old;
    	if(index < 0)
    		index += table.length;
    	if(table[index] == null)
    		return null;
    	
    	for(Entry<K,V> nextItem : table[index]) {
    		if(nextItem.key.equals(key)) {
    			old = nextItem.value;
    			table[index].remove(nextItem);
    			return old;
    		}
    			
    	}
    	return null;
    }

    // throws UnsupportedOperationException
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException() ;
    }

    // empties the table
    @Override
    public void clear() {
    	// Fill HERE
    	for(int i = 0; i < table.length; i++) {
    		table[i] = null;
    	}
    		
    	numKeys = 0;
    }

    // returns a view of the keys in set view
    @Override
    public Set<K> keySet() {
    	// FILL HERE
    	
    	//create a hash set equal to numkeys
    	HashSet<K> keys = new HashSet<>(numKeys);
    	Iterator<K> iter = keys.iterator();
    	while(iter.hasNext())
    		keys.add(iter.next());
    	return keys;
    	
    }

    // throws UnsupportedOperationException
    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException() ;
    }


    // returns a set view of the hash table
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
    	// FILL HERE
    	//return the entry set made previously and returning the values
    	return new EntrySet();
    	
    }

    @Override
    public boolean equals(Object o) {
    	// FILL HERE
    	if (!(o instanceof Map)) return false;
    	Map<K,V> temp = (Map<K,V>)o;
    	
    	return entrySet().equals(temp.entrySet());
    	
    }

    @Override
    public int hashCode() {
    	//FILL HERE
    	//make a temporary normal hashtable
    	Hashtable<K,V> temp = new Hashtable<>();
    	
    	//add all the table items to the temporary table
    	for(int i = 0; i < table.length; i++) {
    		if(table[i] != null) {
    			for(Entry<K,V> item : table[i]) {
    				if(item != null)
    					temp.put(item.key, item.value);
    			}
    		}
    	}
    	
    	//use that table's hashcode
    	return temp.hashCode();
    }
}
