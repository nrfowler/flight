
insert into ftd_flight.location(id, latitude, longitude, city) values (1, 35.0457, -85.3096, 'CHATTANOOGA'), (2, 35.9606, -83.9207, 'KNOXVILLE'), (3, 36.1627, -86.7816, 'NASHVILLE'), (4, 35.1495, -90.0490, 'MEMPHIS') ON CONFLICT DO NOTHING
