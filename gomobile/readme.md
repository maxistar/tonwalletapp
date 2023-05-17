### How to complie gomobile

```
# install gomobile executable
go get -d golang.org/x/mobile/cmd/gomobile

# compile aar
gomobile bind -o ../app/hello.aar -target=android ./hello

```

on mac
```
gomobile bind -o app/hello.aar -androidapi 19 -target=android ./hello
```
