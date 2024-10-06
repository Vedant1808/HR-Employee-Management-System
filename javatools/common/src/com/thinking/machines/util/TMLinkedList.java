package com.thinking.machines.util;
public class TMLinkedList<T> implements TMList<T>
{
class TMNode<T>
{
public T data;
public TMNode<T> next;
TMNode()
{
this.data=null;
this.next=null;
}
}
public class TMLinkedListIterator<T> implements TMIterator<T>
{
private TMNode<T> ptr;
public TMLinkedListIterator(TMNode<T> ptr)
{
this.ptr=ptr;
}
public boolean hasNext()
{
return this.ptr!=null;
}
public T next()
{
if(ptr==null) throw new InvalidIteratorException("Iterator has no more elements");
T data=this.ptr.data;
this.ptr=this.ptr.next;
return data;
}
}
public TMIterator<T> iterator()
{
return new TMLinkedListIterator<T>(this.start);
}
private TMNode<T> start;
private TMNode<T> end;
private int size;
public TMLinkedList()
{
this.start=null;
this.end=null;
this.size=0;
}
public void add(T data)
{
TMNode<T> node=new TMNode<T>();
node.data=data;
if(this.start==null)
{
this.start=node;
this.end=node;
}
else
{
this.end.next=node;
this.end=node;
}
this.size++;
}
public void add(int index,T data)
{
if(index<0) throw new IndexOutOfBoundsException("Invalid index : "+index);
if(index>=this.size)
{
this.add(data);
return;
}
TMNode<T> node=new TMNode<>();
node.data=data;
TMNode<T> j;
TMNode<T> k=null;
j=this.start;
for(int e=0;e<index;e++) 
{
k=j;
j=j.next;
}
if(j==this.start)
{
node.next=this.start;
this.start=node;
}
else
{
k.next=node;
node.next=j;
}
this.size++;
}
public void insert(int index,T data)
{
this.add(index,data);
}
public T remove(int index)
{
if(index<0 || index>=this.size) throw new IndexOutOfBoundsException("Invalid index : "+index);
TMNode<T> j;
TMNode<T> k=null;
j=this.start;
for(int e=0;e<index;e++) 
{
k=j;
j=j.next;
}
T data=j.data;
if(this.start==this.end)
{
this.start=null;
this.end=null;
}
else if(j==this.start)
{
this.start=this.start.next;
}
else if(j==this.end)
{
k.next=null;
this.end=k;
}
else
{
k.next=j.next;
}
this.size--;
return data;
}
public void removeAll()
{
this.size=0;
this.start=null;
this.end=null;
}
public void clear()
{
this.size=0;
this.start=null;
this.end=null;
}
public int size()
{
return this.size;
}
public T get(int index)
{
if(index<0 || index>=this.size) throw new IndexOutOfBoundsException("Invalid index : "+index);
TMNode<T> node;
node=this.start;
for(int e=0;e<index;e++) node=node.next;
return node.data;
}
public void update(int index,T data)
{
if(index<0 || index>=this.size) throw new IndexOutOfBoundsException("Invalid index : "+index);
TMNode<T> node;
node=this.start;
for(int e=0;e<index;e++) node=node.next;
node.data=data;
}
public void copyTo(TMList<T> other)
{
other.clear();
for(int e=0;e<this.size();e++) other.add(this.get(e));
}
public void copyFrom(TMList<T> other)
{
this.clear();
for(int e=0;e<other.size();e++) this.add(other.get(e));
}
public void appendTo(TMList<T> other)
{
for(int e=0;e<this.size();e++) other.add(this.get(e));
}
public void appendFrom(TMList<T> other)
{
for(int e=0;e<other.size();e++) this.add(other.get(e));
}
public void forEach(TMListItemAcceptor<T> a)
{
if(a==null) return;
TMNode<T> t;
for(t=this.start;t!=null;t=t.next) a.accept((T)t.data);
}
}