# Cinema

## Ilkin giriş üçün curl
```curl
curl --location 'localhost:8585/auth/login' \
--header 'Content-Type: application/json' \
--data '{
    "username":"veli456",
    "password":"password123"
}'
```

---
-  Genre əlavə etmək

```curl
curl -X 'POST' \
  'http://localhost:8585/genre' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '[
  {
    "name": "ACTION"
  },
  {
    "name": "ADVENTURE"
  },
  {
    "name": "HORROR"
  },
  {
    "name": "SCIENCE"
  },
  {
    "name": "CRIME"
  },
  {
    "name": "WAR"
  },
  {
    "name": "SPORT"
  }
]'
```

---
- Film əlavə etmək
```curl
curl --location 'http://localhost:8585/movie' \
--header 'accept: */*' \
--header 'Content-Type: application/json' \
--data '{
  "name": "Sonik 3",
  "movieDescription": "Sonik, Nakls və Teyls, daha əvvəl qarşılaşmadıqları güclərə sahib olan sirli bir düşmən — Kirpi Şedou ilə mübarizə aparmaq üçün yenidən bir araya gəlirlər. Onların bacarıqlarını bir çox cəhətdən üstələyən Şedou ilə üzləşən Sonikın komandası, Şedounu dayandırmaq və planeti qorumaq ümidi ilə gözlənilməz bir ittifaq qurmağa məcbur olur.",
  "userAge": 6,
  "imdbRate": 7.5,
  "realiseYear": 2024,
  "status": "ACTIVE",
  "genreIds": [
    1,3,4
  ],
  "duration": 180,
  "author": "Ceff Fauler",
  "movieSessionRequests": [
    {
      "date": "2025-01-25T10:34:23.865Z",
      "hallId": 1,
      "langId": 1,
      "sessionId": 1
    },
    {
      "date": "2025-01-25T12:34:23.865Z",
      "hallId": 2,
      "langId": 2,
      "sessionId": 2
    }
  ]
}'
```

- Film Gətirmək APİ
```curl
curl --location 'http://localhost:8585/movie' \
```

--- 
- Ticket əlavə etmək
```curl
curl --location --request POST 'http://localhost:8585/ticket/3' \
```

- Ticketlərin siyahısı
```curl
curl --location 'http://localhost:8585/ticket/3' \
```

- Bileti almaq
```curl
curl --location --request POST 'http://localhost:8585/ticket/buy/5' \
```

- Bileti qaytarmaq 
```curl
curl --location --request POST 'http://localhost:8585/ticket/return/5' \
```

- İstifadəçi biletləri
```curl
curl --location 'http://localhost:8585/ticket/user-tickets' \
```