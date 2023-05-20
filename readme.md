

### Requirements

minimum of dependencies
Go

### Todo

- [x] create a splash screen 
  - is there are better way? https://developer.android.com/develop/ui/views/launch/splash-screen
  - how to hide action bar? android:theme="@android:style/Theme.DeviceDefault.NoActionBar" does not work
- [ ] create activities
      - https://developer.android.com/topic/libraries/architecture/viewmodel
- [ ] write interactions in marmaid markdown

## How to run tests

### Instrumentation Tests

in folder androidTest run using android studio

### Unit Tests

in folder unitTest run using android studio

address is EQB9sKy3ziopLGzwS8sKtz1QIt0EiPfPVSf30A5UrXHVvtjc


### Todo:

- [ ] security code form
  - [ ] check the code validity
  - [ ] create a model
  - [ ] create interactions between models
  - [ ] use security form when application starts
- [ ] select what to do form
- [ ] create form to enter the secret phrase 
  - [ ] create success layout (it is not needed now)
  - [x] words autosuggestion
    - [x] copy real words from the library
    - [x] replace all components with the autosuggest
    - [x] connect all components in the code
    - [x] make another form the same way
- [x] create the layout to check the code we skipped earlier
  - [ ] check words for correctness
  - [ ] store wallet in the application
- [x] remove not needed files
- [ ] business logic, store the created wallet
- [ ] settings pages
  - [ ] make delete wallet to be red
  - [ ] use better components for settings
  - [ ] list of wallet types
- [ ] wallet activity
  - [ ] show transactions and new icon
- [ ] send dialog
  - [ ] send money 
- [ ] receive dialog
  - [x] add text labels 
  - [x] customise form according mock
  - [ ] use real wallet value
- [ ] scan wallet barcode
  - [x] basic scaning implementation 

### UI Tasks

- [ ] make forms exactly styled like on screenshots as possible
- [ ] create launch icon
- [ ] style buttons and texts

### Links:

- [Figma Design](https://www.figma.com/file/KYK17IdM2ldAAZL540G2hV/TON-Wallet-%C2%B7-Android?type=design&node-id=0-1&t=vzLRrmDAN2Ki4yqm-0)
- [репозиторий конкурса](https://github.com/ton-community/wallet-contest)
    - `git clone git@github.com-maxistar:maxistar/tonwallet.git`
- [video lessons](https://www.youtube.com/watch?v=GcqFhoUuNNI)
- [репозиторий приложения](https://github.com/maxistar/tonwallet)
- [ADNL протокол](https://docs.ton.org/develop/dapps/apis/adnl)
- [tonlib](https://github.com/ton-blockchain/ton/tree/master/example/android)

