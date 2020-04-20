# API Automation of CRUD activities in SLACK
 CRUD operations performed using API Slack methods -  `channels in slack`.
## The automation tech stack:
`Language : Java`

`Api Testing Framework   : Rest assured Client`

`Build Tool :  Maven` 

`Reporting and Test Management : testNG`

### Operations done:

Prerequisite done: Created an app 'Web hooks' in slack and installed the app in the personal slack workspace.Generated an authToken for access.

#### Scenarios tested:
1.Created a slack channel using `conversations.create` method in slack api.  
2.Joined the channel using `conversations.join` with the channel created in last step.  
3.Renamed the channel using `conversations.join` method.  
4.Listing the available channels in the workspace using   `conversations.list`.  
5.Archived the channel using `communications.archive`  
6.Verified the archived channel.


### How to execute the testNG suite?
##### To run this using IDE:
      Clone this repository into any IDE and run the testngAPI.xml file.

