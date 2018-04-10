Data dashboard Readme file

To start the dashboard run following command:
	docker-compose up

After the application is started it can be browsed using address:
	http://localhost:8080/dashboard

Description
	The application visualizes yield and other metrics based on generated csv files. You can choose a file from the list displayed on the dashboard.
In this version only files included in jar can be viewed, more data files can be viewed if added to /dataDashboard/src/main/resources/data/ folder (or directly to .jar),
however this requires rebuilding the jar and docker image.
After selecting the file beside the yield, it is also possible to see graphs for temperature, component and product flow which you can switch between using links on top menu.
The "Source data" option shows table with source data used for visualization.
Note, that if you will not choose any file and use any of the top menu links data for default file will be shown (the file attached to the task description).

Directory 'dataDashboard' contains the source file of the application, so they can be viewed.
