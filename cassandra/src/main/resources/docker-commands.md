``dse-server``
````
MBP-de-Luiz:Documents luizcosta$ docker pull datastax/dse-server:latest
Error response from daemon: manifest for datastax/dse-server:latest not found: manifest unknown: manifest unknown
MBP-de-Luiz:Documents luizcosta$ docker pull datastax/dse-server:6.7.2
6.7.2: Pulling from datastax/dse-server
be8881be8156: Pull complete 
87afd40ffc1e: Pull complete 
46c26bf1484f: Pull complete 
6f4da1ccc624: Pull complete 
8645fb599946: Pull complete 
7c0936c04e46: Pull complete 
8325faff2bae: Pull complete 
f555a87ef4d6: Pull complete 
8cbdf77dfc89: Pull complete 
c9e79ff7a3a1: Pull complete 
884b9b6f5cfd: Pull complete 
b7625cd0626b: Pull complete 
Digest: sha256:42dd884a46d1ca5fdbb5460ae4d1c8fde23b6d007f52db0e762a5a4b2e5e6163
Status: Downloaded newer image for datastax/dse-server:6.7.2
docker.io/datastax/dse-server:6.7.2
````
``dse-studio``
````
MBP-de-Luiz:Documents luizcosta$ docker pull datastax/dse-studio:6.8.4
6.8.4: Pulling from datastax/dse-studio
6ec8c9369e08: Pull complete 
3aa4e9b77806: Pull complete 
1a9b6b4d80d1: Pull complete 
845c172f556e: Pull complete 
68ab8b21369a: Pull complete 
a136fbb7d9e1: Pull complete 
54308a9e41b1: Pull complete 
9785cb22b52c: Pull complete 
1768f04584ff: Pull complete 
d2d6cbefe868: Pull complete 
Digest: sha256:155e1712c9948ee0b6236b971b118e343009219deaa60c8dc1bbc44af976e9e8
Status: Downloaded newer image for datastax/dse-studio:6.8.4
docker.io/datastax/dse-studio:6.8.4
````

````
MBP-de-Luiz:Documents luizcosta$ docker run -e DS_LICENSE=accept --memory 4g --name my-dse -d datastax/dse-server:6.8.4 -g -s -k
Unable to find image 'datastax/dse-server:6.8.4' locally
6.8.4: Pulling from datastax/dse-server
6ec8c9369e08: Already exists 
3aa4e9b77806: Already exists 
1a9b6b4d80d1: Already exists 
845c172f556e: Already exists 
68ab8b21369a: Already exists 
a136fbb7d9e1: Already exists 
af9f63bcd249: Pull complete 
ead782f664c8: Pull complete 
5948fbf3a08e: Pull complete 
ce64d307cd61: Downloading  49.91MB/795.8MB
47a1f7ba0bdf: Downloading  39.66MB/63.39MB
bba4817b4bf5: Download complete 
````

````
MBP-de-Luiz:Documents luizcosta$ docker run -e DS_LICENSE=accept --memory 4g --name my-dse -d datastax/dse-server:6.8.4 -g -s -k
Unable to find image 'datastax/dse-server:6.8.4' locally
6.8.4: Pulling from datastax/dse-server
6ec8c9369e08: Already exists 
3aa4e9b77806: Already exists 
1a9b6b4d80d1: Already exists 
845c172f556e: Already exists 
68ab8b21369a: Already exists 
a136fbb7d9e1: Already exists 
af9f63bcd249: Pull complete 
ead782f664c8: Pull complete 
5948fbf3a08e: Pull complete 
ce64d307cd61: Pull complete 
47a1f7ba0bdf: Pull complete 
bba4817b4bf5: Pull complete 
Digest: sha256:2843e8f06a23a9c6c1018c262923baa723604acc6a216449b11a2e446c9cc963
Status: Downloaded newer image for datastax/dse-server:6.8.4
e85fcbee464512c515474927a48a267fc7e1b5b6876247bb9c1c932e3e3ffef0
````
````
MBP-de-Luiz:Documents luizcosta$ docker run -e DS_LICENSE=accept --link my-dse -p 9091:9091 --memory 1g --name my-studio -d datastax/dse-studio:6.8.4
5f9fb34daad048ba42b1a5190d13a66e56491b6c116269b0b246ccc39e4b93ec
````