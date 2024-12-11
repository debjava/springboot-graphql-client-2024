package com.ddlab.rnd;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.ddlab.rnd.entity.AppUser;

@Component
public class AutoRun {
	
	public void makeMutationQuery() {
		WebClient webClient = WebClient.create("http://localhost:8080/graphql");
		HttpGraphQlClient graphQlClient11 = HttpGraphQlClient.builder(webClient).build();

		String mutationQuery = """
								mutation CreateAppUser {
				    createAppUser(
				        firstName: "Bagha11"
				        lastName: "Patel11"
				        nickName: "Jangli11"
				        shortDesc: "A Sundar Bagha"
				    )
				}
								""";

		String response = graphQlClient11.document(mutationQuery).retrieve("createAppUser").toEntity(String.class)
				.block();
		System.out.println("Response: " + response);
	}

	public void makeQuery1() {
		WebClient webClient = WebClient.create("http://localhost:8080/graphql");
		HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder(webClient)
				.build();
		String query = """
								query GetAppUserById {
				    getAppUserById(id: "52") {
				        id
				        firstName
				        lastName
				        nickName
				        shortDesc
				    }
				}
								""";

		AppUser appUser = graphQlClient.document(query).retrieve("getAppUserById").toEntity(AppUser.class).block();
		System.out.println("App User Details: " + appUser);
	}
	
	public void makeQuery2() {
		WebClient webClient = WebClient.create("http://localhost:8080/graphql");
		HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder(webClient).build();
		AppUser country = graphQlClient.documentName("getAppUserById")
				.variable("slug", 102).retrieveSync("getAppUserById").toEntity(AppUser.class);
		System.out.println(country);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void run() {
		System.out.println("Application started running ...");
		
//		makeQuery1();
//		makeQuery2();
		makeMutationQuery();

//		WebClient client = WebClient.builder()
//	            .baseUrl("http://localhost:8080/graphql")
//	            .build();
//		HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder(client).build();

//		RestClient restClient = RestClient.create("https://spring.io/graphql");
//		HttpSyncGraphQlClient graphQlClient = HttpSyncGraphQlClient.create(restClient);

		WebClient webClient = WebClient.create("http://localhost:8080/graphql");

		HttpGraphQlClient graphQlClient = HttpGraphQlClient.builder(webClient)
				/* .headers((headers) -> headers.setBasicAuth("joe", "...")) */
				.build();
//		AppUser appUser = graphQlClient.document("GetAppUserById").variable("id", 52).retrieve("id").toEntity(AppUser.class).block();
//		AppUser appUser = graphQlClient.document("GetAppUserById").variable("id", 52).execute().block().toEntity(AppUser.class);
//		System.out.println(appUser);

//		String query = """
//								query GetAppUserById {
//				    getAppUserById(id: "52") {
//				        id
//				        firstName
//				        lastName
//				        nickName
//				        shortDesc
//				    }
//				}
//
//								""";

//		AppUser appUser =  graphQlClient.document(query).retrieve("getAppUserById").toEntity(AppUser.class).block();
//		System.out.println("App User Details: " + appUser);

//		AppUser country = graphQlClient.documentName("getAppUserById")
////			    .variable("id", 52).execute().block().toEntity(String.class);
//				.variable("slug", 52)
//			    .retrieveSync("getAppUserById")
//			    .toEntity(AppUser.class);
////			    .block();
//		System.out.println(country);

//		HttpGraphQlClient graphQlClient11 = graphQlClient.mutate().build();

//		String mutationQuery = """
//								mutation CreateAppUser {
//				    createAppUser(
//				        firstName: "Hati11"
//				        lastName: "Singh11"
//				        nickName: "NewHati11"
//				        shortDesc: "A good Elephant11"
//				    )
//				}
//
//								""";
//
//		String response = graphQlClient11.document(mutationQuery).retrieve("createAppUser").toEntity(String.class)
//				.block();
//		System.out.println("Response: " + response);

//		WebClient  webClient = 
//        .post()
//        .uri("http://localhost:8080/graphql/createEventLog")
//        .bodyValue(BodyInserters.fromValue(body))
//        .retrieve()
//        .bodyToMono(String.class);
		
		

	}
}
