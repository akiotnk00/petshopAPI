package br.com.mjv.petshopAPI.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import br.com.mjv.petshopAPI.entity.Pedido;
import br.com.mjv.petshopAPI.repository.PedidoRepository;

@Service
public class NotificacaoService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public void enviarSMS(Long id) throws Exception {
		
		String msg = pedidoService.confirmaSMSPedido(id);
		
		Optional<Pedido> pedidoBuscado = pedidoRepository.findById(id);
		
		if(!pedidoBuscado.isPresent()) {
			throw new Exception("Não foi possivel localizar o pedido.");
		}
		
		Twilio.init(twilioSid, twilioKey);
		
		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);
		
		Message message = Message.creator(to, from, msg).create();
		
		System.out.println(message.getSid());
		
		
	}

}
