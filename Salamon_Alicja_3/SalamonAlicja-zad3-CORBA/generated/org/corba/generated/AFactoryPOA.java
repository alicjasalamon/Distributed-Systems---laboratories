package org.corba.generated;


/**
* org/corba/generated/AFactoryPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from MiddlewareTestbed.idl
* Friday, April 12, 2013 1:59:25 PM CEST
*/

public abstract class AFactoryPOA extends org.omg.PortableServer.Servant
 implements org.corba.generated.AFactoryOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("create_item", new java.lang.Integer (0));
    _methods.put ("take_item", new java.lang.Integer (1));
    _methods.put ("release_item", new java.lang.Integer (2));
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
       case 0:  // MiddlewareTestbed/AFactory/create_item
       {
         try {
           String name = in.read_string ();
           String type = in.read_string ();
           org.corba.generated.Item $result = null;
           $result = this.create_item (name, type);
           out = $rh.createReply();
           org.corba.generated.ItemHelper.write (out, $result);
         } catch (org.corba.generated.ItemAlreadyExists $ex) {
           out = $rh.createExceptionReply ();
           org.corba.generated.ItemAlreadyExistsHelper.write (out, $ex);
         }
         break;
       }

       case 1:  // MiddlewareTestbed/AFactory/take_item
       {
         try {
           String name = in.read_string ();
           org.corba.generated.Item $result = null;
           $result = this.take_item (name);
           out = $rh.createReply();
           org.corba.generated.ItemHelper.write (out, $result);
         } catch (org.corba.generated.ItemNotExists $ex) {
           out = $rh.createExceptionReply ();
           org.corba.generated.ItemNotExistsHelper.write (out, $ex);
         } catch (org.corba.generated.ItemBusy $ex) {
           out = $rh.createExceptionReply ();
           org.corba.generated.ItemBusyHelper.write (out, $ex);
         }
         break;
       }

       case 2:  // MiddlewareTestbed/AFactory/release_item
       {
         try {
           String name = in.read_string ();
           this.release_item (name);
           out = $rh.createReply();
         } catch (org.corba.generated.ItemNotExists $ex) {
           out = $rh.createExceptionReply ();
           org.corba.generated.ItemNotExistsHelper.write (out, $ex);
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
    "IDL:MiddlewareTestbed/AFactory:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public AFactory _this() 
  {
    return AFactoryHelper.narrow(
    super._this_object());
  }

  public AFactory _this(org.omg.CORBA.ORB orb) 
  {
    return AFactoryHelper.narrow(
    super._this_object(orb));
  }


} // class AFactoryPOA
