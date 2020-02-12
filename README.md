# jfrog-func-tests
Sample suite to demonstrate browser automation Using groovy, Geb, Spock and Gradle 

### Requirements
```
Java 8 or higher
Gradle
Artifactory OSS up and running
```

## Configuration


### Test enviroment details

Can be changed in file JfrogConfig under src/test/resources

```
environments {
	test {
		url = "http://localhost:8081"
		username = "admin"
		password = "password"
	}
}
```

### Spec Data(Search terms)

Datables can be changed in QuickSearchSpec.groovy under src/test/groovy -> specs
```
		goodSearchTerm| _
		"fi"          | _
		"fil"         | _
		"file"        | _
```

### Browser Configuration
Chnage the driver versions in build.gradle to match with browser installed
```
	ext {
		gebVersion = '3.3'
		seleniumVersion = '3.14.0'
		chromeDriverVersion = '80.0.3987.16'
		geckoDriverVersion = '0.26.0'
	}
```

## Usage
following commands will launch the tests with the individual browsers:
```
./gradlew clean chromeTest
./gradlew clean firefoxTest
```

## Reports

Reports can ve viewed in the directory -> build/spock-reports/index.html

