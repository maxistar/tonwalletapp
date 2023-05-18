

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
- [x] select what to do form
  - [x] move existing start page logic to fragments
  - [x] create a second fragment and redirect user there
- [ ] create form to enter the secret phrase 
  - [ ] create 24 inputs
  - [ ] create success layout (it is not needed now)
- [x] create the layout to check the code we skipped earlier
- [x] remove not needed files
- [ ] business logic, store the created wallet
- [ ] settings pages
  - [ ] make it look like on the mock
- [ ] wallet activity
  - [ ] show transactions and new icon
- [ ] send dialog
  - [ ] create basic layouts
    - [ ] fragment 1 recipient
    - [ ] fragment 2 amount
    - [ ] fragment 3 comment
    - [ ] fragment 4 sending
    - [ ] fragment 5 sent
- [ ] receive dialog
  - [ ] customise form accorging mock


### UI Tasks

- [ ] make forms exactly styled like on screenshots as possible

### Do not forget

- [ ] rename main namespace com.example.tonwalletactivities 

### Links:

- [Figma Design](https://www.figma.com/file/KYK17IdM2ldAAZL540G2hV/TON-Wallet-%C2%B7-Android?type=design&node-id=0-1&t=vzLRrmDAN2Ki4yqm-0)
- [репозиторий конкурса](https://github.com/ton-community/wallet-contest)
    - `git clone git@github.com-maxistar:maxistar/tonwallet.git`
- [video lessons](https://www.youtube.com/watch?v=GcqFhoUuNNI)
- [репозиторий приложения](https://github.com/maxistar/tonwallet)
- [ADNL протокол](https://docs.ton.org/develop/dapps/apis/adnl)
- [tonlib](https://github.com/ton-blockchain/ton/tree/master/example/android)

