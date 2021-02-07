# GameboyPrinterReceiver

> Transfer image from your GameBoy Camera to your computer by emulating a GameBoy Printer.

[![forthebadge](https://forthebadge.com/images/badges/built-with-grammas-recipe.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/contains-technical-debt.svg)](https://forthebadge.com)

This small Java application connects to a GameBoy Printer Emulator (see https://github.com/mofosyne/arduino-gameboy-printer-emulator) and waits for images. Once you print an image from your GameBoy Camera, the app will decode the image and save it to your computer as a PNG at a resolution of 160 by 144 pixels. This application runs in the command line and has no GUI.

## Important Note
Currently, this Java application only works with GameBoy Printer Emulator V1 and V2. I will update thie repository in the future to support V3.

## Installation

Download the precompiled .jar-file from releases or open the project in an IDE of your choosing.

## Usage

Plug in your GameBoy Printer Emulator (see https://github.com/mofosyne/arduino-gameboy-printer-emulator) and run the application:

```bash
java -jar gameboy-printer-emulator.jar COM1
```

You can omit the serial port (in this case, COM1) and the application will try to connect to the first serial port it can find. Any images are saved to the same directory as the .jar-file is in.

## Used Libraries

Thank you to mofosyne and his Arduino GameBoy Printer Emulator (https://github.com/mofosyne/arduino-gameboy-printer-emulator). Also a big thank you to KodeMunkie and his gameboycameralib (https://github.com/KodeMunkie/gameboycameralib) and to Fazecast's jSerialComm (https://fazecast.github.io/jSerialComm/)!

## Release History

* 1.0.0
    * First release
* 1.0.1
     * Updated Gameboy Camera Lib

## Meta

lukasklinger � [@lukespccontrol](https://twitter.com/lukespccontrol) � [lukasklinger.com](https://lukasklinger.com)

[https://github.com/lukasklinger](https://github.com/lukasklinger/)

[https://git.lukasklinger.com/lukas](https://git.lukasklinger.com/lukas)

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b feature/fooBar`)
3. Commit your changes (`git commit -am 'Add some fooBar'`)
4. Push to the branch (`git push origin feature/fooBar`)
5. Create a new Pull Request
