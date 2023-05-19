### How to complie gomobile


```
# install gomobile executable
go get -d golang.org/x/mobile/cmd/gomobile

# compile aar
gomobile bind -o ../app/hello.aar -target=android ./wallet

```

on mac

```
gomobile bind -o app/hello.aar -androidapi 19 -target=android ./wallet
```

Test locally:

`go run main.go`

Todo: 

[ ] rename hello to wallet
[ ] add api url as an argument
[ ] return walled address
[ ] get the list of secret words for an application