
curl -i -v -X GET -H "Content-Type:application/json" -H "Accept: application/json"  http://10.233.44.111:9091/member/james
curl -i -v -X POST -H "Content-Type:application/json" -H "Accept: application/json"  http://10.233.44.111:9091/member -d  '{"name":"james","comment":"test"}'