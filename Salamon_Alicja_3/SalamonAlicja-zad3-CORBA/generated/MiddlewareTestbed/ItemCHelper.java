package MiddlewareTestbed;


/**
* MiddlewareTestbed/ItemCHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from MiddlewareTestbed.idl
* Friday, April 12, 2013 1:58:38 PM CEST
*/

abstract public class ItemCHelper
{
  private static String  _id = "IDL:MiddlewareTestbed/ItemC:1.0";

  public static void insert (org.omg.CORBA.Any a, MiddlewareTestbed.ItemC that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static MiddlewareTestbed.ItemC extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (MiddlewareTestbed.ItemCHelper.id (), "ItemC");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static MiddlewareTestbed.ItemC read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_ItemCStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, MiddlewareTestbed.ItemC value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static MiddlewareTestbed.ItemC narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof MiddlewareTestbed.ItemC)
      return (MiddlewareTestbed.ItemC)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      MiddlewareTestbed._ItemCStub stub = new MiddlewareTestbed._ItemCStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static MiddlewareTestbed.ItemC unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof MiddlewareTestbed.ItemC)
      return (MiddlewareTestbed.ItemC)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      MiddlewareTestbed._ItemCStub stub = new MiddlewareTestbed._ItemCStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
