package ohm.softa.a02;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList, Iterable {
    private Element head;
    private int size;

    public SimpleListImpl()
    {
       head = null;
       size = 0;
    }

    // add new object as head of list
    @Override
    public void add(Object o) {
        Element tmp = this.head;
        this.head = new Element(o, tmp);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleListImpl output = new SimpleListImpl();
        for(Object o : this)
        {
            if(filter.include(o)){
                output.add(o);
            }
        }
        return output;
    }

    private static class Element
     {
         private Object item;
         private Element next;

         private Element(Object i, Element n){
             item = i;
             next = n;
         }
     }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private Element current = head;

            //falls hasNext = false -> wir gehen nicht mehr in next rein!!!
            @Override
            public boolean hasNext() {
                return (current != null);
            }

            @Override
            public Object next() {
                Element tmp = current;
                current = current.next;
                return tmp.item;
            }
        };
    }


}
