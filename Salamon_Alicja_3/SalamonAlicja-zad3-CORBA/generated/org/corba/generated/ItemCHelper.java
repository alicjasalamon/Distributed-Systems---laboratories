package org.corba.generated;


/**
* org/corba/generated/ItemCHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from MiddlewareTestbed.idl
* Friday, April 12, 2013 1:59:26 PM CEST
*/

abstract public class ItemCHelper
{
  private static String  _id = "IDL:MiddlewareTestbed/ItemC:1.0";

  public static void insert (org.omg.CORBA.Any a, org.corba.generated.ItemC that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.corba.generated.ItemC extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (org.corba.generated.ItemCHelper.id (), "ItemC");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static org.corba.generated.ItemC read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ItemCStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.corba.generated.ItemC value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static org.corba.generated.ItemC narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof org.corba.generated.ItemC)
      return (org.corba.generated.ItemC)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      org.corba.generated._ItemCStub stub = new org.corba.generated._ItemCStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static org.corba.generated.ItemC unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof org.corba.generated.ItemC)
      return (org.corba.generated.ItemC)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      org.corba.generated._ItemCStub stub = new org.corba.generated._ItemCStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
