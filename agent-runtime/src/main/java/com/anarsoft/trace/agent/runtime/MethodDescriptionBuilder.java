package com.anarsoft.trace.agent.runtime;




import com.vmlens.shaded.gnu.trove.list.TLinkable;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;

import com.anarsoft.trace.agent.serialization.FieldAccessDescription;
import com.anarsoft.trace.agent.serialization.MethodDescription;

public class MethodDescriptionBuilder implements TLinkable<MethodDescriptionBuilder> {

	private TLinkedList<TLinkableWrapper<FieldAccessDescription>> fieldDescriptionList = new TLinkedList<TLinkableWrapper<FieldAccessDescription>>();
	private TLinkedList<TLinkableWrapper<String>> annotationList = new TLinkedList<TLinkableWrapper<String>>();
	
	
	private final String name;
	private final String desc;
	private final int access;
	private final int id;
	private int lineNumber = -1;

	private ClassVisitorCreateDesc classVisitorCreateDesc;



	public MethodDescriptionBuilder(String name, int id,String desc,int access,ClassVisitorCreateDesc classVisitorCreateDesc) {
		super();
		this.name = name;
		this.id = id;
		this.desc = desc;
		this.access = access;
		
		this.classVisitorCreateDesc = classVisitorCreateDesc;
	}




	public TLinkedList<TLinkableWrapper<FieldAccessDescription>> getFieldDescriptionList() {
		return fieldDescriptionList;
	}


   public void addAnnotation(String name)
   {
	   annotationList.add( new  TLinkableWrapper<String>(name)); 
   }






	public int getId() {
		return id;
	}









	public MethodDescription build() {


		FieldAccessDescription[] array = new FieldAccessDescription[fieldDescriptionList.size()];

		//array = fieldDescriptionList.toArray(array);

		int index = 0;

		for(TLinkableWrapper<FieldAccessDescription> f :  fieldDescriptionList  )
		{

			array[index] = f.getElement();

			index++;
		}


        String[] annotationArray = new String[annotationList.size()];		
		
		 index = 0;

			for(TLinkableWrapper<String> f :  annotationList  )
			{

				annotationArray[index] = f.getElement();

				index++;
			}

			
	    
			

		return new MethodDescription(name, id , array , desc, access , lineNumber  );


	}
	
	
	public void setLineNumber(int in)
	{
		if( lineNumber == -1 )
		{
			lineNumber = in;
		}
	}
	
	

	private MethodDescriptionBuilder next;
	private MethodDescriptionBuilder previous;




	@Override
	public MethodDescriptionBuilder getNext() {
		// TODO Auto-generated method stub
		return next;
	}




	@Override
	public MethodDescriptionBuilder getPrevious() {
		// TODO Auto-generated method stub
		return previous;
	}




	@Override
	public void setNext(MethodDescriptionBuilder arg0) {

		next = arg0;

	}




	@Override
	public void setPrevious(MethodDescriptionBuilder arg0) {
		previous = arg0;

	}

}
