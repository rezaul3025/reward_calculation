# reward_calculation

Tech-stack:

    Java 1.8		

    Build tool : Maven	

    Packaging : jar 

    Spring components:
	
        Spring boot	

        Spring Rest for RESTful Web-Service 

        Spring data  

        Spring web  
    
    Datebase: H2 (for testing , use when test profile is active, Configuration - >  application-test.properties)
              MySQL(for development, use as a doker container, configure with micro service, use when dev profile is active, Configuration - >  application-dev.properties)
        
    Microservice: 
        Microservice management is done with docker-compose, Services : 1. reward_service 2. reward_service_test
    
Build, Run & test
    
    Using docker-compose:
        Build & run: $ docker-compose up  
            - It will create docker image, create and start container with all services running. 
            - This must be the first command
        Test: 
            - Microservices can be test with test service at ->  http://localhost:8000
        
        Note: dev profile is activated 
              application-dev.properties is used
              MySQL database is used
    
    Using Maven:
        Build & test:
            - Go to the folder 'reward_calculation' run -> $ mvn clean install
        Run Microservices:
            - Go to the folder 'reward_calculation/reward_service' run ->$ mvn spring-boot:run
            - Go to the folder 'reward_calculation/reward_service_test' run ->$ mvn spring-boot:run
        Test:
            - Microservices can be test with test service at ->  http://localhost:8000
        
        Note: test profile is activated
              application-test.properties is used
              H2(Memeory) database is used
              

Micro service documentation:

1. Add exercise(steps) for user:
    
    Url: /exercises
    	
    Method: POST
    	 
    Request body : 
    
        {
            "steps": 32323, 
            "userId": 1
        }
        
    Response:
    
        {
            "id":1,
            "steps":32323,
            "date":"2019-03-31 21:14",
            "type":"RUNNING",
            "reward":null
        }
        
2. Calculate reward

    Url: /rewards/user/1
    
    Method: POST
    
    Request body: {}
    
    Response:
    
        [
            {
                "id":1,
                "priceInEuro":"32.32",
                "convertedPrice":"27.83",
                "type":"CASH",
                "date":"2019-03-31 21:22"
            },
            {
                "id":2,
                "priceInEuro":"3.23",
                "convertedPrice":"2.78",
                "type":"CASH",
                "date":"2019-03-31 21:22"
            }
        ]
        
3. Show exercise & reward

    Url: /exercises/user/1
    
    Method: GET
    
    Response:
    
        [
            {
                "id":1,
                "steps":32323,
                "date":"2019-03-31 21:15",
                "type":"RUNNING",
                "reward":
                {
                    "id":1,
                    "priceInEuro":"32.32",
                    "convertedPrice":"27.83",
                    "type":"CASH",
                    "date":"2019-03-31 21:22"
                }
             },
             {
                "id":2,
                "steps":3232,
                "date":"2019-03-31 21:22",
                "type":"RUNNING",
                "reward":
                {
                    "id":2,
                    "priceInEuro":"3.23",
                    "convertedPrice":"2.78",
                    "type":"CASH",
                    "date":"2019-03-31 21:22"
                }
             }
        ]
        
4. Show reward by user

    Url: /rewards/user/1
    
    Method: GET
    
    Response:
    
        [
            {
                "id":1,
                "priceInEuro":"32.32",
                "convertedPrice":"27.83",
                "type":"CASH","date":"2019-03-31 21:22"
            },
            {
                "id":2,
                "priceInEuro":"3.23",
                "convertedPrice":"2.78",
                "type":"CASH",
                "date":"2019-03-31 21:22"
            }
        ]
        
5. Show all exercise
    
    Url: /exercises
    
    Method: GET
    
    Response:
    
        [
            {
                "id":1,
                "steps":32323,
                "date":"2019-03-31 21:15",
                "type":"RUNNING",
                "reward":
                    {"
                        id":1,
                        "priceInEuro":"32.32",
                        "convertedPrice":"27.83",
                        "type":"CASH",
                        "date":"2019-03-31 21:22"
                    }
            },
            {
                "id":2,
                "steps":3232,
                "date":"2019-03-31 21:22",
                "type":"RUNNING",
                "reward":
                     {
                        "id":2,
                        "priceInEuro":"3.23",
                        "convertedPrice":"2.78",
                        "type":"CASH",
                        "date":"2019-03-31 21:22"
                     }
            }
        ]
            
6. Show exercises by user 

    Url: /exercises/user/1
    
    Method: GET
    
    Response:
    
          [
            {
                "id":1,
                "steps":32323,
                "date":"2019-03-31 21:15",
                "type":"RUNNING",
                "reward":
                    {
                        "id":1,
                        "priceInEuro":"32.32",
                        "convertedPrice":"27.83",
                        "type":"CASH",
                        "date":"2019-03-31 21:22"
                    }
            },
            {
                "id":2,
                "steps":3232,
                "date":"2019-03-31 21:22",
                "type":"RUNNING",
                "reward":
                    {
                        "id":2,
                        "priceInEuro":"3.23",
                        "convertedPrice":"2.78",
                        "type":"CASH",
                        "date":"2019-03-31 21:22"
                    }
            }
          ]    

    
        
                  

Note :

    - Two test users are created during service startup at -> com.reward.api.data.InitialDataGeneration.java
    - This users are create only when dev or test profile is active
    - Open exchange service is mock for testing and mock is available when active profile is test at -> com.reward.api.service.external.mock.MockOpenExchangeApiService.java 
    - Real Open exchange service is available when dev profile is active and dev profile is activated when services running using docker-compose
                             
