### ⭐️ Goal

**MongoDB 공식 Documentation을 스스로 보면서 학습할 수 있을 정도로 성장하자.**

**목차**
1. [MongoDB 개요](#1.-MongoDB-개요)
2. [Document Query](#2.-Document-Query)
3. [읽기와 쓰기에 대한 제어](#3.-읽기와-쓰기에-대한-제어)
4. [MongoDB의 다양한 인덱스](#4.MongoDB의-다양한-인덱스)

## 1. MongoDB 개요

### MongoDB란?

기존 Database의 단점 보완을 위해 Custom Data Store 개발

확장성과 신속성에서 자주 문제가 발생

Database 개발 → MongoDB

### MongoDB를 선택하는 이유

- Schema가 자유롭다.
- HA와 Scale-Out Solution을 자체적으로 지원해서 확장이 쉽다.
- Secondary Index를 지원하는 NoSQL이다.
- 다양한 종류의 Index를 제공한다.
- 응답 속도가 빠르다.
- 배우기 쉽고 간편하게 개발이 가능하다.

**MongoDB는 유연하고 확장성 높은 Open Source Document 지향 Database이다.**

### SQL vs NoSQL

**SQL**
→ 데이터를 엑셀처럼 행, 열로 구성한다.

- **장점**
    - 데이터 중복을 방지할 수 있다.
    - Join의 성능이 좋다.
    - 복잡하고 다양한 쿼리가 가능하다.
    - 잘못된 입력을 방지할 수 있다.
- **단점**
    - 하나의 레코드를 확인하기 위해 여러 테이블을 Join하여 가시성이 떨어진다.
    - 스키마가 엄격해서 변경에 대한 공수가 크다.

**Scaling 관점**

Scale-Out → 수평적 확장(분산 DB 등)
Scale-Up → 수직적 확장(성능 업 등)

- **SQL 장단점**
    - Scale-Out이 가능하지만, 설정이 어렵다.
    - 확장할 때마다 App단에 수정이 필요하다.
    - 전통적으로 Scale-Up 위주로 확장했다.

왜 이렇게 제한하고 복잡할까?
→ 관계형 데이터베이스는 1970년대부터 사용되었는데, Disk Storage가 매우 고가의 제품으로 데이터 중복을 줄이는데 집중했다.

**NoSQL**
→ 관계형 데이터베이스로 하지 못한 것을 할 수 있다.

순차적으로 **MongoDB , redis , HBASE , neo4j** 에 해당된다.

![image](https://user-images.githubusercontent.com/78959175/233288075-795a65f5-8505-4cb7-b9e3-5c960ba3bce6.png)

[참고하면 좋은 자료](https://learn.microsoft.com/ko-kr/dotnet/architecture/cloud-native/relational-vs-nosql-data)

![image](https://user-images.githubusercontent.com/78959175/233288145-a38651bf-1951-4749-8d78-154650d70583.png)
![image](https://user-images.githubusercontent.com/78959175/233288457-fddfb93c-8a32-43b8-a187-c85b8053aea3.png)



**좌: SQL, 우: NoSQL**

- **장점**
    - 데이터 접근성과 가시성이 좋다.
    - Join없이 조회가 가능해서 응답 속도가 일반적으로 빠르다.
    - 스키마 변경에 공수가 적다.
    - 스키마가 유연해서 데이터 모델을 App의 요구사항에 맞게 데이터를 수용할 수 있다.
- **단점**
    - 데이터의 중복이 발생한다.
    - 스키마가 자유롭지만, 스키마 설계를 잘해야 성능 저하를 피할 수 있다.

**Scaling 관점**

- **NoSQL 장단점**
    - HA와 Sharding에 대한 솔루션을 자체적으로 지원하고 있어 Scale-Out이 간편하다.
    - 확장 시, App의 변경사항이 없다.

⭐️ **MongoDB 정리**

- **MongoDB는 Document 지향 DB이다.**
- **데이터 중복이 발생할 수 있지만, 접근성과 가시성이 좋다.**
- **스키마 설계가 어렵지만, 스키마가 유연해서 App의 요구사항에 맞게 데이터를 수용할 수 있다.**
- **분산에 대한 소루션으 자체적으로 지원해서 Scale-Out이 쉽다.**
- **확장 시, App을 변경하지 않아도 된다.**

### MongoDB 구조

| RDBMS |  | MongoDB |
| --- | --- | --- |
| Cluster | ↔ | Cluster |
| Database | ↔ | Database |
| Table | ↔ | Collection |
| Row | ↔ | Document |
| Column | ↔ | Field |

| Database | Description |
| --- | --- |
| admin | • 인증과 권한 부여 역할이다.
• 일부 관리 작업을 하려면 admin Database에 대한 접근이 필요하다. |
| local | • 모든 mongod instance는 local database를 소유한다.
• oplog와 같은 replication 절차에 필요한 정보를 저장한다.
• startup_log와 같은 instance 진단 정보를 저장한다.
• local database 자체는 복제되지 않는다. |
| config | • sharded cluster에서 각 shard의 정보를 저장한다. |

**Collection 특징**

- 동적 스키마를 갖고 있어서 스키마를 수정하려면 필드 값을 추가/수정/삭제하면 된다.
- Collection 단위로 Index를 생성할 수 있다.
- Collection 단위로 Shard를 나눌 수 있다.

**Document 특징**

- JSON 형태로 표현하고 BSON(Binary JSON) 형태로 저장한다.
- 모든 Document에는 “_id” 필드가 있고, 없이 생성하면 ObjectId 타입의 고유한 값을 저장한다.
- 생성 시, 상위 구조인 Database나 Collection이 없다면 먼저 생성하고 Document를 생성한다.
- Document의 최대 크기는 **16MB**이다.

**요약**

- **Database → Collection → Document → Field 순으로 구조가 형성되어 있다.**
- **admin, config, local database는 MongoDB를 관리하는 데 사용된다.**
- **Collection은 동적 스키마를 갖는다.**
- **Document는 JSON 형태로 표현되고 BSON 형태로 저장된다.**
- **Document는 고유한 “_id”필드를 항상 갖고 있다.**
- **Document의 최대 크기는 16MB로 고정되어 있다.**

### MongoDB 배포 형태

- **Standalone 형태**
**클라이언트 ↔ 서버 - 주로 스터디, 테스트 환경**
- **Replica Set 형태**
**- HA(High Availability) 고가용성** - 현업에서 제일 많이 사용

    ![image](https://user-images.githubusercontent.com/78959175/233288765-7e0d3fef-2d4a-4f1d-9927-012d6adc6efa.png)

    
- **Sharded Cluster 형태**
**- HA(High Availability) 고가용성
- Distribution**
    
    ![image](https://user-images.githubusercontent.com/78959175/233288845-5718d162-1604-43b0-9083-1a0882471f12.png)
    

### Replica Set

| Status | Description |
| --- | --- |
| Primary | • Read/Write 요청 모두 처리할 수 있다.
• Write를 처리하는 유일한 멤버이다.
• Replica Set에 하나만 존재할 수 있다. |
| Secondary | • Read에 대한 요청만 처리할 수 있다.
• 복제를 통해 Primary와 동일한 데이터 셋을 유지한다.
• Replica Set에 여러개가 존재할 수 있다. |

**[Replica Set 관련 공식 문서](https://www.mongodb.com/docs/manual/core/replica-set-architecture-three-members/)**

**요약**

- **Replica Set은 HA 솔루션이다.**
- **데이터를 들고 있는 멤버의 상태는 Primary와 Secondary가 있다.**
- **Secondary는 선출을 통해 과반수의 투표를 얻어서 Primary가 될 수 있다.**
- **Arbiter는 데이터를 들고 있지 않고 Primary 선출 투표에만 참여하는 멤버이다.**
- **Replica Set은 local database의 Oplog Collection을 통해 복제를 수행한다.**

### Sharded Cluster

Sharding은 큰 데이터를 분할하는 것을 의미하고,
분할된 데이터는 Shard라고 한다.

| 장점 | 단점 |
| --- | --- |
| • 용량의 한계를 극복할 수 있다.
• 데이터 규모와 부하가 크더라도 처리량이 좋다.
• 고가용성을 보장한다.
• 하드웨어에 대한 제약을 해결할 수 있다. | • 관리가 비교적 복잡하다.
• Replica Set과 비교해서 쿼리가 느리다. |

| Sharding | Partitioning |
| --- | --- |
| • 하나의 큰 데이터를 여러 서브셋으로 나누어 여러 인스턴스에 저장하는 기술
(여러 장비) | • 하나의 큰 데이터를 여러 서브셋으로 나누어 하나의 인스턴스의 여러 테이블로 나누어 저장하는 기술
(하나의 장비에 여러 테이블) |

| Replica Set | Sharded Cluster |
| --- | --- |
| • Replica Set은 각각 멤버가 같은 데이터 셋을 갖는다. | • Sharded Cluster는 각각 Shard가 다른 데이터의 서브셋을 갖는다. |

**[관련 자료 링크](https://www.interviewbit.com/mongodb-interview-questions/)**

![image](https://user-images.githubusercontent.com/78959175/233289975-592d60f2-6a5e-4f01-848c-d55d25666289.png)

**Ranged Sharding**
→ 일반적인 몽고디비 인덱스를 생성하고 해당 필드들에 샤드키를 지정하면 **샤드키의 값을 기준**으로 청크의 범위를 할당하는 방식

**데이터가 균형있게 분산되지 않을 가능성이 높다는 게 치명적인 단점**

**Hashed Sharding**
→ Hashed 인덱스를 필드에 생성하고, 그 필드들을 기준으로 샤드 키를 지정하면 된다.
→ 균등하게 분산이 잘 된다.
→ 카디널리티가 낮으면 완전히 막을 수는 없고, 분산되어 있어서 브로드 캐스트 쿼리, 즉 모든 샤드에 필터링을 거쳐야 할 수도 있다.
→ 80~90%는 이 방식을 사용한다.

**Zone Sharding**
→ 함께 사용하는 샤딩이다.
→ 값에 대해서 Zone을 생성하고 특정 위치에 데이터를 저장할 수 있다.
→ 지역별로 데이터를 분산시켜야 하는 경우, IP를 기준으로 분산시켜야 하는 경우 등

**요약**

- **Sharded Cluster는 MongoDB의 분산 솔루션이다.**
- **Collection 단위로 Sharding이 가능하다.**
- **Sharding은 Shard Key를 선정해야 하고 해당 필드에는 Index가 만들어져 있어야 한다.**
- **꼭 Router를 통해 접근한다.
→ 고하? 도큐먼트라고, 마이그레이션 과정에서 삭제되어야 하는데 있는 애들 혹은 중복 데이터가 보일 수 있어서.**
- **Range와 Hashed Sharding 두 가지 방법이 있다.**
- **가능하면 Hashed Sharding을 통해 분산한다.**

### Replica Set vs Sharded Cluster 어떻게 배포할 것인가?

2가지를 고려하면 된다.

1. 서비스 요구사항 확인 (읽기가 많은지, 쓰기가 많은지 등)
2. 배포 환경 확인 (준비할 수 있는 서버의 스펙 등)

- **예시 1**
매일 1GB씩 데이터가 증가하고 3년간 보관 → **환경에 따라 다르다.**
    
    
    | Storage | Value |
    | --- | --- |
    | Data | 1GB*365*3year=1TB |
    | Index | 1TB * 0.3 = 0.3TB |
    | local Backup | 1.3TB |
    | Oplog | 0.1TB |
    | Total | 2.7TB |
- **예시 2**
Write 요청이 압도적으로 많은 서비스 → **Sharded Cluster
Replica Set은 Write에 대한 분산이 불가능하다.**
    
    
    | Storage | Value |
    | --- | --- |
    | Data | 1GB*365*3year=1TB |
    | Index | 1TB * 0.3 = 0.3TB |
    | local Backup | 1.3TB |
    | Oplog | 0.1TB |
    | Total | 2.7TB |
- **예시 3**
논리적인 데이터베이스가 많은 경우 → **여러 Replica Set으로 분리**
    
    
    | Storage | Value |
    | --- | --- |
    | Data | permanently 1GB per day |
    | Index | 1TB * 0.3 = 0.3TB |
    | local Backup | 1.3TB |
    | Oplog | 0.1TB |
    | Total | 2.7TB |

**Replica Set vs Sharded Cluster**

| 배포 형태 | 장점 | 단점 |
| --- | --- | --- |
| Replica Set | • 운영이 쉽다.
• 장애발생시문제해결및복구가쉽다.
• 서버 비용이 적게 든다.
• 성능이 좋다.
• 개발 시 설계가 용이하다. | • Read에 대한 분산이 가능하지만, Write에 대한 분산은 불가능하다. |
| Sharded Cluster | • Scale-Out이 가능하다.
• Write에 대한 분산이 가능하다. | • Replica Set의 모든 장점이 상대적으로 단점이 된다. |

| 배포 형태 | 배포하는 경우 |
| --- | --- |
| Replica Set | • 가능하면 Replica Set으로 배포한다. |
| Sharded Cluster | • 서비스의 요구사항이 Replica Set으로 충족하지 못할 때 |

### MongoDB Storage Engines

**Storage Engine이란?**

- 데이터가 메모리와 디스크에 어떻게 저장하고 읽을지 관리하는 컴포넌트이다.
- MySQL과 동일하게 Plugin 형태로 되어 있어 MongoDB도 다양한 Storage Engine을 사용할 수 있다.
- MongoDB 3.2부터 MongoDB의 기본 Storage Engine은 WiredTiger이다.
(기존에는 MMAPv1 사용)
- WiredTiger가 도입되면서 MongoDB의 성능은 큰 폭으로 좋아졌다.

| 항목 | MMAPv1 | WiredTiger |
| --- | --- | --- |
| Data Compression | 지원하지 않는다. | 지원한다. |
| Lock | 버전에 따라 Database 혹은 Collection 레벨의 Lock | Document 레벨의 Lock |

## 3. Document Query

### SQL vs MQL

SQL - RDBMS
MQL(MongoDB Query Language) - MongoDB

### Aggregation이란?

- Collection의 데이터를 변환하거나 분석하기 위해 사용하는 집계 프레임워크
- Aggregation은 find 함수로 처리할 수 없는, SQL의 Group By와 Join 구문 같은 복잡한 데이터 분석 기능들을 제공한다.
- Aggregation 프레임워크는 **파이프라인** 형태를 갖춘다.
- MongoDB 2.2부터 제공되었고 이전에는 Map Reduce를 사용했다.

![image](https://user-images.githubusercontent.com/78959175/233290094-4c01607c-c31a-4f13-80ac-c8e6392f8a7c.png)

[MongoDB 공식 문서 Aggregation Framework 보러가기](https://www.mongodb.com/docs/v6.0/core/aggregation-pipeline/)

**작은 팁!**
`$` - $getField라 생각하면 된다.
`$$` - 변수라고 생각하면 된다.

## 4. 읽기와 쓰기에 대한 제어

### Read Preference

- **Primary** → 무조건 Primary로 읽기 요청
- **Primary Preferred** → 가능하면 Primary에서 읽고 없으면 Secondary로 요청
- **Secondary** → 무조건 Secondary로 읽기 요청
- **Secondary Preferred** → 가능하면 Secondary에서 읽고 없으면 Primary로 요청
- **Nearest** → 평균 ping 시간을 기반으로 지연율이 가장 낮은 멤버로 요청

### Write Concern

[공식 문서](https://www.mongodb.com/docs/manual/core/replica-set-write-concern/)

![image](https://user-images.githubusercontent.com/78959175/233290170-a97ff847-a9c9-4e29-b04d-ab869917848a.png)

### Read Concern

Read Concern Level

- **Local** → 복제를 확인하지 않고 요청한 Member의 현재 데이터를 반환
- **Available** → local과 동일하지만, 고아 Document를 반환할 수 있다.
- **Majority** → Member 과반수가 들고 있는 동일한 데이터를 반환
- **Linearizable** → 쿼리 수행 전에 모든 Majority Write가 반영된 결과를 반환
- **Snapshot** → 특정 시점에 대한 결과를 반환 (Point-In-Time Query)

## 4.  MongoDB의 다양한 Index

### MongoDB의 index 기본 구조와 효율적인 탐색

Database에서 Query를 빠르고 효율적으로 실행하기 위해 Index를 사용한다.

Index 내부 구조

![image](https://user-images.githubusercontent.com/78959175/233290241-dd94d38a-1d10-4360-9c24-166e95b32dc2.png)

[MongoDB Index 공식 문서 바로가기](https://www.mongodb.com/docs/manual/indexes/)

다양한 Index들

- **Single Field Index
→ 필드 하나를 가지고 만들어진 인덱스**
- **Compound Index
→ 두 개 필드 이상으로 만들어진 인덱스**
- **Multikey Index
→ 배열 필드의 인덱스(주의사항이 많음!)**
- **Geospatial Index
→ 지역, 좌표를 기반으로 특정 지역이나 범위 내의 도큐먼트 인덱스**
- **Wildcard Index
→ 내장된 도큐먼트 내의 특정할 수 없는 필드들이 많을 때 유연하게 사용하는 인덱스**
- **Text Index
→ 검색 기능을 제공하는 텍스트 인덱스 - 한글 지원 X**
- **Hashed Index
→ Sharded Cluster에서 사용**

인덱스의 속성으로 사용할 수 있는 옵션과 같은 것들

- TTL Index
→ 일정 시간이 지나면 인덱스를 자동으로 삭제하는 인덱스, 매우 유용한 인덱스!
- Unique Index
→ 컬렉션을 생성하면 _id 필드에 유니크 인덱스가 생성
- Partial & Sparse Index
→ 특정 조건 및 존재하는 필드에만 인덱스를 생성
- Case Insensitive Index
→ 대소문자 구분하지 않는 인덱스
- Hidden Index
→ 쿼리 플래너로부터 인덱스를 숨김

### E-S-R Rule

- E - Equality First
- E → R - Equality Before Range
`db.games.createIndex({ gamertag: 1, level: 1 })` 
`db.games.find({ gamertag: “Ace”, level: { $gt: 50 }})`
- E → S - Equality Before Sort
`db.games.createIndex({ gamertag: 1, score: 1 })` 
`db.games.find({ gamertag: “Ace” }).sort({ score: 1 })`
- S → R - Sort Before Range
`db.games.createIndex({ score: 1, level: 1 })` 
`db.games.find({ level: { $gt: 50}}).sort({ score: 1 })`
- E → S → R - Equality Sort Range
`db.games.createIndex({ gamertag: 1, score: 1, level: 1 })` 
`db.games.find({ gamertag: “Ace”, level: { $gt: 50 }}).sort({ score: 1 })`

**Multikey Index 비용**
→ 실제 Collection에 Document를 저장하는 비용을 1이라고 가정하면 Index key entry를 추가하고 삭제하는 작업은 1.5 정도의 비용이 필요하다고 한다.

### Index 생성 주의사항

4.2 버전 이전까지 background 옵션을 설정하지 않으면 Index를 빠르게 생성할 수 있지만, Database 단위의 Lock을 걸어서 Index 생성이 완료될 때 까지 Read/Write가 막힌다.

**Version >= 4.2 : background option이 default.**

**Rolling Index Builds**

4.4 이전 까지 Index는 내부적으로 Primary에서 생성 완료하고 Secondary에 복제한다.

Index 생성으로 인해서 발생하는 성능저하를 줄이기 위해 멤버 하나씩 접속해서 Rolling 형태로 Index를 생
성했다.

→ 하지만 너무 번거롭다.
→ Unique Index는 Collection에 대해서 Write가 없다는 것을 확인하고 생성해야한다.
→ Index 생성 시간이 Oplog Window Hour보다 작아야한다.

**Drop Index**

4.4 이전 까지 Index는 내부적으로 Primary에서 생성 완료하고 Secondary에 복제한다. Primary에서 생
성 완료하고 Secondary로 복제하는 도중에 Index를 Drop하면, Secondary에서 복제를 멈추는 문제가 있
다. 

즉, Index가 큰 경우, 복제 지연이 발생할 수 있다.

**Version >= 4.4 : Primary와 Secondary에서 동시에 Index가 생성된다.**

**Resumable Index Build**

버전 5.0 부터, Index 생성 중에 정상적으로 process가 shutdown되면 다시 기동 되었을 때 기존의 progress에 이어서 Index가 생성된다.

**비정상적으로 shutdown된 경우는 처음부터 Index를 다시 생성한다.**

**내장된 Document Index 생성**

필터링에서 Document의 모든 필드의 순서도 같을 때만 Index를 사용하기 때문에 내장 Document 필드 자체에 Index를 만드는 것은 피한다.

**내장 Document 안의 구체적인 필드에 Index를 생성한다.**

### MongoDB join 방식

**MongoDB는 Left Outer Join 형태로만 Join을 지원하고 Lookup이라고 한다.**

### Query Planner 읽는 방법

[MongoDB cursor.explain 공식 문서 바로가기](https://www.mongodb.com/docs/v6.0/reference/method/cursor.explain/#mongodb-method-cursor.explain)
