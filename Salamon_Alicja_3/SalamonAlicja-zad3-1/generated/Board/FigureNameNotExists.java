package Board;


/**
* Board/FigureNameNotExists.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Board.idl
* Wednesday, April 17, 2013 3:31:24 PM CEST
*/

public final class FigureNameNotExists extends org.omg.CORBA.UserException
{

  public FigureNameNotExists ()
  {
    super(FigureNameNotExistsHelper.id());
  } // ctor


  public FigureNameNotExists (String $reason)
  {
    super(FigureNameNotExistsHelper.id() + "  " + $reason);
  } // ctor

} // class FigureNameNotExists
