package init;

import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Libro;
import interfaces.LibroRemote;

public class Libreria {

	public static void main(String[] args) {
		try {
			String appName = "PruebaEJBEAR";
			String moduleName = "PruebaEJB";
			String beanName = "Libros";
			String remoteInterface = "interfaces.LibroRemote";
			String name = String.format("ejb:%s/%s/%s!%s", appName, moduleName, beanName, remoteInterface);
			Context ctx = new InitialContext();
			LibroRemote proxy = (LibroRemote) ctx.lookup(name);

			proxy.save(new Libro(null, "1asduio23", new Date(), 100.0));
			proxy.save(new Libro(null, "3asduio12", new Date(), 120.0));
			proxy.save(new Libro(null, "1asduio23123", new Date(), 130.0));

			System.out.println(proxy.find(2L));
			System.out.println(proxy.find(3L));
//			System.out.println(proxy.find(300L));

			for (Object o : proxy.list()) {
				System.out.println(o);
			}

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
