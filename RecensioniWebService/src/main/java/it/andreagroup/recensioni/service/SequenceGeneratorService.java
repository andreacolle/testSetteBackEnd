package it.andreagroup.recensioni.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import it.andreagroup.recensioni.data.entity.DatabaseSequence;

@Service
public class SequenceGeneratorService {

	    private MongoOperations mongoOperations;

	    @Autowired
	    public SequenceGeneratorService(MongoOperations mongoOperations) {
	        this.mongoOperations = mongoOperations;
	    }

	     long generateSequence(String seqName) {

	        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
	                new Update().inc("seq",1), options().returnNew(true).upsert(true),
	                DatabaseSequence.class);
	        return !Objects.isNull(counter) ? counter.getSeq() : 1;

	    }

		private Object options() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	

