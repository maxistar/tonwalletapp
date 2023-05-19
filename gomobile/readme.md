### How to complie gomobile


```
# install gomobile executable
go get -d golang.org/x/mobile/cmd/gomobile

# compile aar
gomobile bind -o ../app/wallet.aar -target=android ./wallet

```

on mac

```
gomobile bind -o app/wallet.aar -androidapi 19 -target=android ./wallet
```

Test locally:

`go run main.go`

Todo: 

[x] rename hello to wallet
[x] add api url as an argument
[x] return walled address
[x] get the list of secret words for an application (seed.go)
[x] return balance
[ ] return last transactions
 - [ ] create json structure