# Elgamal_Encryption_Decryption
Implemention of Elgamal Algorithm

## Global Public Elements

* p is a random prime number

* alpha is a random number which is less than p

## Key Generation

* Select private XA where XA < p-1

* Calculate YA = alpha^XA mod p

* Public key = {p,alpha,YA}

* Private key = {XA}

## Encryption

* plaintext m < p

* Select random integer k(small) < p

* Calculate K = YA^k mod p

* Calculate c1 = alpha^k mod p

* Calculate c2 = K*m mod p

* Cipher is {c1,c2}

## Decryption

* Cipher is {c1,c2}

* Calculate K = c1^XA mod p

* Get K inverse (K^-1) using EEA algorithm

* Plaintext m = c2*K^-1 mod p
