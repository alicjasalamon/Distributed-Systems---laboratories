package Board;


/**
* Board/FigureHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Board.idl
* Wednesday, April 17, 2013 3:31:24 PM CEST
*/

abstract public class FigureHelper
{
  private static String  _id = "IDL:Board/Figure:1.0";

  public static void insert (org.omg.CORBA.Any a, Board.Figure that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static Board.Figure extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (Board.FigureHelper.id (), "Figure");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static Board.Figure read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_FigureStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, Board.Figure value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static Board.Figure narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Board.Figure)
      return (Board.Figure)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Board._FigureStub stub = new Board._FigureStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static Board.Figure unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof Board.Figure)
      return (Board.Figure)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      Board._FigureStub stub = new Board._FigureStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
