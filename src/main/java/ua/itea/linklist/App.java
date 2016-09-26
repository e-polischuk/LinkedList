package ua.itea.linklist;

import java.util.concurrent.CountDownLatch;

/**
 * The LinkedList testdrive
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        LinkedList<String> list = new LinkedList<String>();
        
        for(int i = 1; i <= 5; i++) {
            list.addFirst("elem_" + i);
        }
        System.out.println("After adding by 'addFirst': size = " + list.getSize());
        System.out.println(list);
        
        for(int i = 1; i <= 5; i++) {
            list.addLast("elem_" + i);
        }
        System.out.println("\nAfter adding by 'addLast': size = " + list.getSize());
        System.out.println(list);
        
        for(int i = 0; i <= 20; i = i + 2) {
            list.add("|", i);
        }
        System.out.println("\nAfter adding by 'add': size = " + list.getSize());
        System.out.println(list + "\n");
        
        for(int i = 0; i <= 10; i++) {
            System.out.print(list.remove(i) + " ");
        }
        System.out.println("\nAfter removing by 'remove': size = " + list.getSize());
        System.out.println(list + "\n");
        
        Iterator<String> it1 = list.getIterator();
        while(it1.hasNext()) {
            System.out.println("it1 index = " + it1.getIndex());
            list.add("<0>", it1.getIndex());
            it1.next();
        }
        System.out.println("After adding with 'it1': size = " + list.getSize());
        System.out.println(list + "\n");
        
        Iterator<String> it2 = list.getIterator();
        while(it2.hasNext()) it2.next();
        while(it2.hasPrevious()) {
            System.out.println("it2 index = " + it2.getIndex());
            list.add(">|<", it2.getIndex());            
            it2.previous();
        }
        System.out.println("After adding with 'it2': size = " + list.getSize());
        System.out.println(list + "\n");
        
        Iterator<String> it = list.getIterator();
        while(it.hasNext()) {
            System.out.println("it index = " + it.getIndex());
            list.remove(it.getIndex());
            it.next();
            it.next();
        }
        it.restart();
        while(it.hasNext()) {
            System.out.println("it index = " + it.getIndex());
            list.remove(it.getIndex());
            it.next();
            it.next();
        }
        System.out.println("After deleting with 'it': size = " + list.getSize());
        System.out.println(list + "\n");
        
        System.out.println("\nCheck 'ConcurrentModificationException' in two separate threads:");
        final LinkedList<String> listcheck = list;
        final CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(new Runnable() {

	    public void run() {
		Iterator<String> it3 = listcheck.getIterator();
		while(it3.hasNext()) {
//	            System.out.println("it3 index = " + it3.getIndex());
	            listcheck.add("IT_3", it3.getIndex());            
	            it3.next();
	        }
		latch.countDown();
	    }
            
        });
        
        Thread t2 = new Thread(new Runnable() {

	    public void run() {
		Iterator<String> it4 = listcheck.getIterator();
		while(it4.hasNext()) {
//	            System.out.println("it3 index = " + it4.getIndex());
	            listcheck.add("IT_4", it4.getIndex());
//		    listcheck.remove(it4.getIndex());
	            it4.next();
	        }
		latch.countDown();
	    }
            
        });
        
        t1.start();
        t2.start();
        
        try {
	    latch.await();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
        
        System.out.println("After deleting with 'it': size = " + list.getSize());
        System.out.println(list + "\n");
    }
    
    
    
}
