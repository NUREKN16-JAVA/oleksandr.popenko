/*
 * Generated by XDoclet - Do not edit!
 */
package com.mockrunner.example.ejb.interfaces;

/**
 * Home interface for PaySession.
 */
public interface PaySessionHome
   extends javax.ejb.EJBHome
{
   public static final String COMP_NAME="java:comp/env/ejb/PaySession";
   public static final String JNDI_NAME="com/mockrunner/example/PaySession";

   public com.mockrunner.example.ejb.interfaces.PaySession create()
      throws javax.ejb.CreateException,java.rmi.RemoteException;

}
