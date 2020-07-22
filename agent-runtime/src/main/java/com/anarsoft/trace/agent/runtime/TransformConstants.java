package com.anarsoft.trace.agent.runtime;

public class TransformConstants {

	public final String callback_class_field_access;
	public final String  callback_class_synchronized_statement;
	public final String callback_class_lock_statement ;
	public final String interface_name;
	public final String callback_class_method_enter;
	public final String field_name;
	public final String set_method;
	public final String get_method;
	


	public TransformConstants(String callback_class_field_access,
			String callback_class_synchronized_statement,
			String callback_class_lock_statement,
			String interface_name,
			 String callback_class_method_enter, String field_name, String set_method , String get_method) {
		super();
		this.callback_class_field_access = callback_class_field_access;
		this.callback_class_synchronized_statement = callback_class_synchronized_statement;
		this.callback_class_lock_statement = callback_class_lock_statement;
		this.interface_name = interface_name;
		this.callback_class_method_enter = callback_class_method_enter;
		this.field_name = field_name;
		this.set_method = set_method;
		this.get_method = get_method;
	}




}
