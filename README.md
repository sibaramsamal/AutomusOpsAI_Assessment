# Data Collector Service  

## Setup Instructions  

1. **Clone the Repository**  
   Clone this repository to your local machine.  

2. **Database Configuration**  
   - Create a database named **datacollector**.  
   - Update the database credentials in the `application.properties` file to match your machine's configuration.  

3. **API Configuration**  
   - The project currently uses static local APIs for fetching and saving data.  
   - These can be replaced with actual APIs by updating the respective API URLs in `application.properties`.  

4. **Running the Project**  
   - Open the project in **IntelliJ IDEA** or any Java IDE.  
   - Run the application.  

5. **Access the UI**  
   - Open your web browser and go to:  
     **[http://localhost:8081/web/api/v1/datacollector](http://localhost:8081/web/api/v1/datacollector)**  

## Expected Output  
After completing the above steps, the UI will display the metrics data in a tabular format.  
![Image](https://github.com/user-attachments/assets/15b9f564-f0fb-425b-89a7-83b58df69c8e)
