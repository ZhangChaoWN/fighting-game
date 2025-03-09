# A Role-Playing Game

This is the code repository for a Role-Playing Game developed in Java. Players can explore the virtual world, fight enemies, gain experience, and have fun!

## How to Test, Build, and Run the Project

This project includes the Gradle wrapper, so you can run commands starting with `./gradlew` on Linux and macOS, or `.\gradlew.bat` on Windows, without manually installing Gradle.

### Commands

- Run tests
```sh
./gradlew test
```

- Generate test coverage report
```sh
./gradlew jacocoTestReport
```

- Build and run the game
```sh
./gradlew shadowJar
java -jar app/build/libs/my-app-jar-1.0.jar
```
**Note**: the game will fail to start if you run it directly with `./gradlew run` because Gradle redirects standard input.

- Check format and run tests
```sh
./gradlew check
```

- Format code
```sh
./gradlew :app:spotlessApply
```