package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.backend.Entities.Admin;
import com.example.backend.dao.AdminRepo;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner{
	

	@Autowired
	private AdminRepo _ormAdmin;

	@Autowired
	PasswordEncoder passEncode;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Add first admin if not exist
		if(_ormAdmin.findAll().isEmpty())
		{
			var _newAdmin = Admin.builder().username("admin").password(passEncode.encode("admin")).build();
			var newAd=_ormAdmin.save(_newAdmin);
			System.out.println("newAd: "+newAd);
		}
	}

}
