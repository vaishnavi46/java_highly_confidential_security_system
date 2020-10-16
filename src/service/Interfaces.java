package service;
import model.Admin;
import utility.ClassNotException;
public interface Interfaces {
	boolean LoginAdmin(Admin admin) throws ClassNotException;
}
