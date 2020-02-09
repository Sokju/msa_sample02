kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep $1 | awk '{print $1}') 

