package org.corba.generated;


/**
* org/corba/generated/ItemNotExists.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from MiddlewareTestbed.idl
* Friday, April 12, 2013 1:59:25 PM CEST
*/

public final class ItemNotExists extends org.omg.CORBA.UserException
{

  public ItemNotExists ()
  {
    super(ItemNotExistsHelper.id());
  } // ctor


  public ItemNotExists (String $reason)
  {
    super(ItemNotExistsHelper.id() + "  " + $reason);
  } // ctor

} // class ItemNotExists
