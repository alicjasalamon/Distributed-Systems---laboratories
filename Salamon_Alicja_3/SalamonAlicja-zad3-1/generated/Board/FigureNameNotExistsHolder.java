package Board;

/**
* Board/FigureNameNotExistsHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Board.idl
* Wednesday, April 17, 2013 3:31:24 PM CEST
*/

public final class FigureNameNotExistsHolder implements org.omg.CORBA.portable.Streamable
{
  public Board.FigureNameNotExists value = null;

  public FigureNameNotExistsHolder ()
  {
  }

  public FigureNameNotExistsHolder (Board.FigureNameNotExists initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Board.FigureNameNotExistsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Board.FigureNameNotExistsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Board.FigureNameNotExistsHelper.type ();
  }

}