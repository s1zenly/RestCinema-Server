package ru.hse.softwear.cinemaworld.restServer.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import ru.hse.softwear.cinemaworld.restServer.view.model.OccupiedPlace;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class OccupiedPlaceSerializer implements RedisSerializer<List<OccupiedPlace>> {
    @Override
    public byte[] serialize(List<OccupiedPlace> occupiedPlaces) {
        if(occupiedPlaces == null)  {
            return new byte[0];
        }

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(occupiedPlaces);
            objectOutputStream.flush();

            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new SerializationException("Error serialization", e);
        }
    }

    @Override
    public List<OccupiedPlace> deserialize(byte[] bytes) {
        if(bytes == null || bytes.length == 0) {
            return null;
        }

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            return (List<OccupiedPlace>) objectInputStream.readObject();
        } catch (Exception e) {
            throw new SerializationException("Error deserialization", e);
        }
    }
}
