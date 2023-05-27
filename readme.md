# Ton Wallet

this project is part of Ton [Wallet Contest](https://github.com/ton-community/wallet-contest)

## Libraries used

In order to work with Ton Network the [tonutils-go](https://github.com/xssnick/tonutils-go) was used.

Scanner library [zxing-android-embedded](https://github.com/journeyapps/zxing-android-embedded)

## Gomobile

Gomobile is a tool for building and running mobile apps written in Go.

To install

```shell
go install golang.org/x/mobile/cmd/gomobile@latest
gomobile init
```

compiled wallet.aar already included to the repository but it is always good idea to compile the one from scratch.

in order to compile aar library do the following:

```shell
cd gomobile
gomobile bind -o ../app/wallet.aar -target=android ./wallet
```

see more on [gomoble documentation page](https://pkg.go.dev/golang.org/x/mobile/cmd/gomobile)


## Roadmap

Gomobile allows to write also iOS applications.
The initial plan for this contest was to provide also ios application using Kotlin Multiplatform but
this plan was too ambitious. The implementation of ios application will be done as a follow up stage.


### Instrumentation Tests

in folder androidTest run using android studio

### Unit Tests

in folder unitTest run using android studio
address is EQB9sKy3ziopLGzwS8sKtz1QIt0EiPfPVSf30A5UrXHVvtjc

## Screens

![](screenshots/Screenshot_2023-05-26_at_16.31.15.png)

![](screenshots/Screenshot_2023-05-26_at_16.31.31.png)

![](screenshots/Screenshot_2023-05-26_at_16.31.48.png)

![](screenshots/Screenshot_2023-05-26_at_16.31.59.png)

![](screenshots/Screenshot_2023-05-26_at_16.32.16.png)

![](screenshots/Screenshot_2023-05-26_at_16.32.29.png)

![](screenshots/Screenshot_2023-05-26_at_16.32.51.png)

![](screenshots/Screenshot_2023-05-26_at_16.33.30.png)

![](screenshots/Screenshot_2023-05-26_at_16.33.49.png)


### Todo:

- [ ] security code form
- [ ] settings pages
  - [ ] create dedicated page for secret code 
  - [ ] create set language
- [ ] wallet activity
  - [ ] store transaction in case there is no internet
  - [ ] can we check the network in background to see if money arrived?
  - [ ] can we show status message on the top as required in the mock?
  - [ ] can we better format icons on the buttons?
  - [ ] for some wallets not all transactions are being shown
  - [ ] storage fee and comments are not being shown
- [ ] send dialog
  - [ ] use paste icon
- [ ] receive dialog
  - [ ] make fonts as provided 
- [ ] security
  - [ ] do not show secret phrase to logs
  - [ ] can se use better method to store the wallet? e.g. secret phrase?
  - [ ] can we cipher it in the application?
- [ ] link for the external intents as described in the requirements

### Links:

- [Figma Design](https://www.figma.com/file/KYK17IdM2ldAAZL540G2hV/TON-Wallet-%C2%B7-Android?type=design&node-id=0-1&t=vzLRrmDAN2Ki4yqm-0)
- [репозиторий конкурса](https://github.com/ton-community/wallet-contest)
    - `git clone git@github.com-maxistar:maxistar/tonwallet.git`
- [video lessons](https://www.youtube.com/watch?v=GcqFhoUuNNI)
- [репозиторий приложения](https://github.com/maxistar/tonwallet)
- [ADNL протокол](https://docs.ton.org/develop/dapps/apis/adnl)
- [tonlib](https://github.com/ton-blockchain/ton/tree/master/example/android)

