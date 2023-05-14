

### Requirements

minimum of dependencies
Go

### Todo

- [x] create a splash screen 
  - is there are better way? https://developer.android.com/develop/ui/views/launch/splash-screen
  - how to hide action bar? android:theme="@android:style/Theme.DeviceDefault.NoActionBar" does not work
- [ ] create activities
      - https://developer.android.com/topic/libraries/architecture/viewmodel
- [x] create a top menu
- [ ] connect go code instead of mocking
- [ ] write interactions in marmaid markdown

## How to run tests

### Instrumentation Tests

in folder androidTest run using android studio

### Unit Tests

in folder unitTest run using android studio


### How to complie gomobile

```
# install gomobile executable
go get -d golang.org/x/mobile/cmd/gomobile

# compile aar
gomobile bind -o app/hello.aar -target=android ./hello

```

on mac
```
gomobile bind -o app/hello.aar -androidapi 19 -target=android ./hello
```

address is EQB9sKy3ziopLGzwS8sKtz1QIt0EiPfPVSf30A5UrXHVvtjc



### Todo:

- [ ] should we extract go app to separate folder to speed up IDE?
- [ ] security code form
  - [ ] create a layout
  - [ ] create a model
  - [ ] create interactions between models

### UI Tassks

- [ ] make forms exactly styled like on screenshots as possible

### Links:

- [Figma Design](https://www.figma.com/file/KYK17IdM2ldAAZL540G2hV/TON-Wallet-%C2%B7-Android?type=design&node-id=0-1&t=vzLRrmDAN2Ki4yqm-0)
- [репозиторий конкурса](https://github.com/ton-community/wallet-contest)
    - `git clone git@github.com-maxistar:maxistar/tonwallet.git`
- [video lessons](https://www.youtube.com/watch?v=GcqFhoUuNNI)
- [репозиторий приложения](https://github.com/maxistar/tonwallet)
- [ADNL протокол](https://docs.ton.org/develop/dapps/apis/adnl)
- [tonlib](https://github.com/ton-blockchain/ton/tree/master/example/android)

