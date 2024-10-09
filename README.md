# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)](https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA)

## My Chess Server Design

my drawn out server design 1st Draft

[![Diagram](Server-design.png)](https://sequencediagram.org/index.html?presentationMode=readOnly#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDABLBoAmCtu+hx7ZhWqEUdPo0EwAIsDDAAgiBAoAzqswc5wAEbBVKGBx2ZM6MFACeq3ETQBzGAAYAdAE5xDAaTgALdvYoALIoAIyY9lAQAK7YMADEaMBUljAASij2SKoWckgQaAkA7r5IYGKIqKQAtAB85JQ0UABcMADaAAoA8mQAKgC6MAD00QZQADpoAN4ARKOUSQC2KDPNMzAzADQbuOpF0Byr61sbKIvASAhHGwC+mMJNMHWs7FyUrbPzUEsraxvbM12qn2UEOfxOMzOFyu4LubE43FgzweolaUEy2XKUAAFBksjlKBkAI7RNRgACU90aoiez0q5laABYnE5JlMNst1MBAqsNgBRKBRFowPQcGDoklk9Z3FEqJ71WTyJQqdStQJgACqY2xXx+lMVimUalU8qMOmaADEkJwYFrKAaRaldcBlpgDcrjbT6vSMEynABmNkc43c378wXQVrABDo4AcVLyADW6Glbu0HvUXoaIhUrTQ0QQCCpOcMz3dRtVIFj5TtOK+Bv16YrJuexmaCg4YtrBuLVBpZabKtUzSrKDkKAU0TAvmxwCnvgbaaVzdNbY7Ysn057stLz3hb2FeMxhLUBawO6zPrArQArCygzNOapQ7yZgKha1sRw1FXiAQCjAEAAGYwJQQrkqmmD7oiWYPO8MCfGMPzrK0xwAnO069BAyZoChGy3L2lCXsgDIwAATPe0xzEhLorDAqH-BsGG+FhOF4ccdzoBwpiRDEcTxNAASGAAMhAWSFPEJRlBUJE1PUcHCp0PQDMMBjqPkaBss6yzbECIIcDKjREXuryIh8GzaWGaE7PowIHK++FwqZxnydSuYwAgYnWtionicSpI5JSF4DsuQ5qigmrapZmx6QcjahZ6rbmjAVo2t22iOjA0UwLFoJLoaQ7EVU14wMyoQPk+L6oe+kZZUk87QEgABeKCHDAdzloV1TIm5KAfNRCy0Q5EK5WC+GpsFCqDsa4VgJuM6WfFBWJfUbYpdaG7zg6OhOjRrqdYlPUlq083br1poHaqMCjuOrHoNiS0Zi2q3JeuMBoCgRQwMxd1oPlT2wb1-WWdcEI-dhKawr2-YmQi8G+da-lkhkqhntDcrdd6sklXerLTMGXI8tVEbCl+P5QH+GmASBYHQBB7VQc5SJHU0wN7b81kzODbFQwpRWkRReOIYNyzDeh86-cNnGcDxUSxAkkQoOgMC+TEzCSaU5SYFepoKa0bTSHywl8r0fKDEMamqBpkzc+ghFIvU0HwfNv2M3DDvZn27nftw5S-diLsQ2gQXnSFy1XeiiwQDQgc4QHEtB49K5Ja0qVijd5SbZhQffTa0goL7YiXS2dLY80ZUVSGRPhh+dXMY1LVtR102Zpjnuou9BZFsFsMHq0GRR1Q0Yo2jPdY8Vt6Ueyj5V1ZNWk9+qi-tg-6FMBoEk-TTnu4D1DwVMhl78z48C1P0vcREcv8eiYrCZiMAAOK0SaGvSdr2O60ZikPyb5v2LRNsE44XtqaJ2wpbZ-TAZ-Y6MAgIbSfpyWcQD0AhxLBdFuw4YDqnmkg7OOEk5dRerNGAp0Mo7W+sgv6xd+a+grvjGehM54k1aNEeq04G6tVTNQtuO4TpbW0OjUsU0EpXXVAgtQD1-rJyIVgiKHl77r2jAgLBz8YDWjNFIrqLMO6zH-pyXkbRdG0QAJLSF5KEAEUkazszFhsHQCBQCJlrMhBiEI9EoAAHK0VcTcGAgxD5e13I7JmrQ745HEaoEeCBzyh1LhPGAuNK6MNfPPT8i9l6r2phvcCqYoE8K-s0Ix+j9ZFJQKY8xljNYoGcUNVxAJ7GOJqaLOpGx3FeOWD4vxDM+ZtyvK0QWbIZjuNUAY0p5TWgWI2FY6pNiWkzAaSAJxszxoAjad48avj-FmBlpfPiCRsDRCgNgbg8BqyGHEcUKp79irQNZu0bofQ-4ALYSxIObI1nLACS5F47soyUPebRdpKBt4HluR3DOKBxHYnEUjQKgiaElWZHjBCBNnzVzfMwkUcZxQoElDkCaodhHh0wdg+cuDXn4M0StM0qcNomlIfITKECqWtziQyOh09KrotSXXBqFNG5cIwbvQJfCtwCMmjIDBrQIUeM+lCqYHzgUEOpWtOAZz3qfUfrRTKiqWUl1cjAsZZj6IwFCAEmGwTflauWLCsAUSYloN6WXBJU9UVVRrrVMmS8KYrypuvWmUAt5u1Bfko+-V3HlJNWakBTr4kDKohG41Ezz6yz2fESwBdPJfQAFIQHURc+ICzEzXPMLc+CnQNQqSGO4wBeD0BsmOcADNUA4AQE8lAbYiavkezAX8utmlpiNuba29tnaTHSBBTBbR7kABWea0BQtzYjXFAUKTwtjaRJFkwUUMLRUw2uoocV4rAASx1RKnqzRwRA5VrcZFp3pfwxl5DmXcLZbQpw5V6Fcv3bVVh9d+WcIZq+g1IqSGPuAOu89zZZpQsTTe56NL1qcBNBc8hia9W7yQFQXMRq8LRolcXVoBzIqUGxImzYDLgAxQccOttdMMMpxIZ2LK7MYCQG+uoCARBxxiiHZQEd0A1EYAgGx3whh0PAfgM65kgYv2zxSZi6MsZ4xseAGxIDQrQ2gennxltdGoBHAAOosGMabIYAAhYSCg4AAGk3HjvMd02Jlq+4wCXWgW19r11vpxq63d7qMW1y9Rkv1NNN65KZsK-eOwaP8f00ZkzZnLPWbs6shzEynNHwRf0vzumBMGbWMZ0zfILNWds-Z5Y4zTUM22RfXi8t4iRCbVJscsBgDYGOYQPIAFX5ax1izcthtjam3NsYGNLmzKd0LMGqdIHwXcDwAoQskiCNSqMAXCKE5lvwdXMlAe0dDBKJY5QVQY7OTbFeBQ-tGgx4-Nc4gVrnnTzRO8yfX0Lqhb+Z5CawLnr0k+syf68LDM8nPE9vvbt2XyJn1q5gIAA)


## Modules

The application has three modules.

- **Client**: The command line program used to play a game of chess over the network.
- **Server**: The command line program that listens for network requests from the client and manages users and games.
- **Shared**: Code that is used by both the client and the server. This includes the rules of chess and tracking the state of a game.

## Starter Code

As you create your chess application you will move through specific phases of development. This starts with implementing the moves of chess and finishes with sending game moves over the network between your client and server. You will start each phase by copying course provided [starter-code](starter-code/) for that phase into the source code of the project. Do not copy a phases' starter code before you are ready to begin work on that phase.

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared test`      | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

## Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```
