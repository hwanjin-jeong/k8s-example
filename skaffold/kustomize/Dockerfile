FROM golang:1.12.9-alpine as builder
COPY src/main.go .
RUN go build -o /app main.go

FROM alpine:3.10
CMD ["./app"]
COPY --from=builder /app .
