package MiddlewareTestbed;


/**
* MiddlewareTestbed/AFactoryOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from MiddlewareTestbed.idl
* Friday, April 12, 2013 1:58:37 PM CEST
*/

public interface AFactoryOperations 
{
  MiddlewareTestbed.Item create_item (String name, String type) throws MiddlewareTestbed.ItemAlreadyExists;
  MiddlewareTestbed.Item take_item (String name) throws MiddlewareTestbed.ItemNotExists, MiddlewareTestbed.ItemBusy;
  void release_item (String name) throws MiddlewareTestbed.ItemNotExists;
} // interface AFactoryOperations
