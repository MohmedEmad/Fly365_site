# Mac Setup Instructions How To Install Java and Maven

1. Install the Java 8 Development Kit (a.k.a.JDK) for your operating system (https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
2. Install HomeBrew on your mac through this commandline from terminal
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
3. Install Maven with Home brew (e.g., brew install maven)
4. Open anew command-prompt and run mvn -version to confirm that it’s working. If it is, it will list the application and its version information. If it’s not working, it will display an error that should help you debug what’s wrong
5. Download and unzip and initial Maven project directory structure (withpre-loaded dependency file) from (https://github.com/selenium-guidebook/init-java/archive/master.zip).

# How to Setup IntelliJ

1. Download and install IntelliJ IDEA Community Edition (https://www.jetbrains.com/idea/download/)
2. Launch IntelliJ and Open the Maven project directory you downloaded earlier by clicking File, Open, traverse to where you unzipped the download, select init-java- master, and click OK. Maven will load all of the requisite dependencies (e.g., JUnit and Selenium) behind the scenes
3. Configure IntelliJ so it uses the JDK by following the SDK configuration instructions (https://www.jetbrains.com/help/idea/sdk.html#manage_sdks)

# How To Install the Browser Driver for Firefox

1. Visit the browser driver download page for Mozilla’s geckodriver (https://github.com/mozilla/geckodriver/releases/latest)
2. Download the latest available version of the browser driver for your operating system
3. Unzip the file (if zipped) and place the file in a known folder on your system
4. Optional: Add this known folder to your system path

# Automation Site Guidelines

Please keep the following format among the whole project

- Elements: Any elements should be written with combination of two phrases {Description}+{FieldType} where Description start with lowercase and FieldTypes as follows TXT: Text, DDL: Drop Down List, BTN: Button, BOX: Check Box, LINK: Link, TAB: Tab, MSG: Message, DPK: Date Picker, HDR: Header, LBL: Label, POPOVER: cabin/class pop over
For example, selectDepartureDateFromDPK
In case of validation messages it should contains 3 phrases {Description}+{FieldName}+{Successful or Warning or Error} 
For example, emptyIDNumberErrorMSG
- Variables: Any variable should be begin with lowercase and capitalize first letters for other words 
For example, emailAddress
- Methods: Any method must be created from feature file with the same step name except general method 
For example, executeDBS
- Class: It should be per page and it's name should be written capitalize first letters+{Test} 
For example, SignUpPageTest
- Feature: It should be per system feature and it's name should be written capitalize first letters 
For example, SignUp.feature
- helper: This folder contains all general classes which configurable for all the project like, TestBase and DataBase Note: Any changes in these classes should be announced among the team before push to master branch

# General Notes

- Test data should be send within feature file except Faker Data
- Scenario Outline should be written for using invalid paramters to the same field having the same expected result
- Scenario with Data table should be written for scenarios required mutliaple data to fill
- Scenarios should be separate if the expected results are different

# How to Run Project from Terminal

- mvn clean test –Pfull
- mvn -Dbrowser=safari clean test –Pfull
- mvn install -Dcucumber.options="--tags @Smoke"
- mvn -Dbrowser=safari install -Dcucumber.options="--tags @Smoke"

# Selenium Grid

- Install hub through java -jar selenium-server-standalone-3.141.59.jar -role hub
- Check hub on selenium grid dashboard through http://localhost:4444/grid/console
- Install node for firefox through java -Dwebdriver.gecko.driver=“/Users/mohamedhassan/Downloads/myGitPRoject-master/Selenium/geckodriver" -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/console -browser browserName=firefox -port 5566
- Install node for chrome through java -jar -Dwebdriver.chrome.driver=“/usr/bin/chromedriver" selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/console -browser browserName=chrome -port 5588


# Deploy and Pull Request Process

- Add new changes local [git add *]
- Commit your changes local [git commit -m “upload discount”]
- Copy your changes [git stash]
- Pull recent version from master [git pull origin master]
- Paste your changes again [git stash apply]
- Resolve conflict if exist
- Run your new script and ensure everything is Ok
- Create temp branch [git checkout -b discount_branch]
- Push your committed changes [git push origin discount_branch]
- Open pull request on stage-test branch through GitHub
- Review and merge
- Delete temp branch [git branch -d discount_branch]
