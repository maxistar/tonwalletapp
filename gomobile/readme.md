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

