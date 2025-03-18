package br.com.postal_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.postal_service.PostalServiceApplication;
import br.com.postal_service.exception.NoContentException;
import br.com.postal_service.exception.NotReadyException;
import br.com.postal_service.model.AddressStatus;
import br.com.postal_service.model.Status;
import br.com.postal_service.model.Address;
import br.com.postal_service.repository.AddressRepository;
import br.com.postal_service.repository.AddressStatusRepository;
import br.com.postal_service.repository.SetupRepository;

@Service
public class PostalService {

	private static final Logger logger = LoggerFactory.getLogger(PostalService.class);

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressStatusRepository statusRepository;

	@Autowired
	private SetupRepository setupRepository;

	@Value("${setup.on.startup}")
	private boolean setupOnStartup;

	@Value("${spring.datasource.url}")
	private String a;

	public Status getStatus() {
		return statusRepository.findById(AddressStatus.DEFAULT_ID)
				.orElse(AddressStatus.builder().status(Status.NEED_SETUP).build()).getStatus();
	}

	public Address getByZipcode(String zipcode) throws NotReadyException, NoContentException {
		if (!this.getStatus().equals(Status.READY))
			throw new NotReadyException();

		return addressRepository.findById(zipcode).orElseThrow(NoContentException::new);
	}

	private void saveServiceStatus(Status status) {
		statusRepository.save(AddressStatus.builder().id(AddressStatus.DEFAULT_ID).status(status).build());
	}

	@Async
	@EventListener(ApplicationStartedEvent.class)
	protected void setupOnStartup() {
		if (setupOnStartup) {
			this.setup();
		}
	}

	public synchronized void setup() {
		logger.info("---" + a);
		logger.info("---");
		logger.info("--- STARTING SETUP");
		logger.info("--- Please wait... This may take a few minutes");
		logger.info("---");
		logger.info("---");

		try {
			if (this.getStatus().equals(Status.NEED_SETUP)) {
				this.saveServiceStatus(Status.SETUP_RUNNING);

				//
				// Download CSV content
				// From origin and saves it.
				this.addressRepository.saveAll(
						setupRepository.listAdressesFromOrigin());

				//
				// Set service READY!
				this.saveServiceStatus(Status.READY);
			}

			logger.info("---");
			logger.info("--- READY TO USE");
			logger.info("---");
		} catch (Exception exc) {
			logger.error("Error to download/save addresses, closing the application....", exc);
			PostalServiceApplication.close(0);
		}
	}

}
