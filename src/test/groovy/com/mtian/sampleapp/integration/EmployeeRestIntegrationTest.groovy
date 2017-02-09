package com.mtian.sampleapp.integration

import static io.restassured.RestAssured.get
import static io.restassured.RestAssured.given
import static io.restassured.RestAssured.with
import static org.hamcrest.CoreMatchers.equalTo
import static org.hamcrest.Matchers.greaterThan
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

import com.mtian.sampleapp.Application
import com.mtian.sampleapp.domain.Employee

import io.restassured.RestAssured

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeRestIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	@Before
	void initPort(){
		RestAssured.port = port;
	}
	
	@Test
	void shouldSaveEmployee(){
		Employee employee = new Employee(name: "Mikhail Tian")

		given()
			.contentType("application/json")
			.body(employee)
			.log().everything()
		.when()
			.post("api/employee")
		.then().assertThat()
			.statusCode(HttpStatus.OK.value())
			.body("name", equalTo ("Mikhail Tian"))
			.body("id", greaterThan (0))
	}
	
	@Test
	void shouldRespondWithCorrectSchema(){
		def employee = new Employee(name: "Mikhail Tian")
		long id = postAndGetIdOf employee
		
		get("api/employee/${id}")
		.then().assertThat()
			.body(matchesJsonSchemaInClasspath("employee-schema.json"))
	}
	
	@Test
	void shouldUpdateEmployee(){
		def employee = new Employee(name: "Mikhail Tian")
		long id = postAndGetIdOf employee
		def updatinigEmployee = new Employee(id: id, name: "Updated Name")
		
		with()
			.contentType("application/json")
			.body(updatinigEmployee)
			.put("api/employee/put/${id}")
			
		get("api/employee/${id}")
		.then().assertThat()
			.statusCode(HttpStatus.OK.value())
			.body("name", equalTo ("Updated Name"))
	}
	
	private postAndGetIdOf(Employee employee){
		with()
			.contentType("application/json")
			.body(employee)
			.post("api/employee")
		.then()
			.extract().jsonPath().getInt("id")
	}
}
