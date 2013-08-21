package Board;


/**
* Board/OvalPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Board.idl
* Wednesday, April 17, 2013 3:31:24 PM CEST
*/

public abstract class OvalPOA extends org.omg.PortableServer.Servant
 implements Board.OvalOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("_get_r1", new java.lang.Integer (0));
    _methods.put ("_set_r1", new java.lang.Integer (1));
    _methods.put ("_get_r2", new java.lang.Integer (2));
    _methods.put ("_set_r2", new java.lang.Integer (3));
    _methods.put ("_get_x", new java.lang.Integer (4));
    _methods.put ("_set_x", new java.lang.Integer (5));
    _methods.put ("_get_y", new java.lang.Integer (6));
    _methods.put ("_set_y", new java.lang.Integer (7));
    _methods.put ("_get_colour", new java.lang.Integer (8));
    _methods.put ("_set_colour", new java.lang.Integer (9));
    _methods.put ("change", new java.lang.Integer (10));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // Board/Oval/_get_r1
       {
         double $result = (double)0;
         $result = this.r1 ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 1:  // Board/Oval/_set_r1
       {
         double newR1 = in.read_double ();
         this.r1 (newR1);
         out = $rh.createReply();
         break;
       }

       case 2:  // Board/Oval/_get_r2
       {
         double $result = (double)0;
         $result = this.r2 ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 3:  // Board/Oval/_set_r2
       {
         double newR2 = in.read_double ();
         this.r2 (newR2);
         out = $rh.createReply();
         break;
       }

       case 4:  // Board/Figure/_get_x
       {
         double $result = (double)0;
         $result = this.x ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 5:  // Board/Figure/_set_x
       {
         double newX = in.read_double ();
         this.x (newX);
         out = $rh.createReply();
         break;
       }

       case 6:  // Board/Figure/_get_y
       {
         double $result = (double)0;
         $result = this.y ();
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 7:  // Board/Figure/_set_y
       {
         double newY = in.read_double ();
         this.y (newY);
         out = $rh.createReply();
         break;
       }

       case 8:  // Board/Figure/_get_colour
       {
         short $result = (short)0;
         $result = this.colour ();
         out = $rh.createReply();
         out.write_short ($result);
         break;
       }

       case 9:  // Board/Figure/_set_colour
       {
         short newColour = in.read_short ();
         this.colour (newColour);
         out = $rh.createReply();
         break;
       }

       case 10:  // Board/Figure/change
       {
         try {
           Board.Parameters params = Board.ParametersHelper.read (in);
           this.change (params);
           out = $rh.createReply();
         } catch (Board.FigureWrongParameters $ex) {
           out = $rh.createExceptionReply ();
           Board.FigureWrongParametersHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Board/Oval:1.0", 
    "IDL:Board/Figure:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public Oval _this() 
  {
    return OvalHelper.narrow(
    super._this_object());
  }

  public Oval _this(org.omg.CORBA.ORB orb) 
  {
    return OvalHelper.narrow(
    super._this_object(orb));
  }


} // class OvalPOA