package com.chanse.configuration.service;

import com.chanse.comsim.domain.client.ComSimClient;
import com.chanse.comsim.domain.dto.SendMessageDto;
import com.chanse.comsim.domain.dto.TransportServiceDto;
import com.chanse.configuration.domain.dto.InterfaceDecoderConfigurationDto;
import com.chanse.configuration.domain.dto.MessageConfigurationDto;
import com.chanse.configuration.domain.dto.TransportConfigurationDto;
import com.chanse.configuration.repository.InterfaceDecoderConfigurationRepository;
import com.chanse.configuration.repository.MessageConfigurationRepository;
import com.chanse.configuration.repository.TransportConfigurationRepository;
import com.chanse.messaging.fields.IntegerDataField;
import com.chanse.messaging.fields.InterfaceDataField;
import com.chanse.messaging.fields.StaticDataField;
import com.chanse.messaging.messages.StandardMessage;
import com.chanse.messaging.msginterface.StaticIdDecoder;
import com.chanse.messaging.transport.TCPClientTransportationService;
import com.chanse.messaging.transport.TCPServerTransportationService;
import com.chanse.messaging.utils.SaveLoadUtils;
import com.chanse.messaging.words.StandardDataWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConfigurationServiceImpl implements ConfigurationService{

    @Autowired
    public ComSimClient myComSimClient;

    @Override
    public List<MessageConfigurationDto> getMessages(String projectName) {
        return null;
    }

    @Override
    public List<MessageConfigurationDto> getMessages(String projectName, String platformName) {
        return null;
    }

    @Override
    public MessageConfigurationDto getMessage(String projectName, String platformName, String messageName) {
        return null;
    }

    @Override
    public MessageConfigurationDto getMessage(UUID specificId) {
        return null;
    }

    @Override
    public boolean editMessage(MessageConfigurationDto messageChanges) {
        return false;
    }

    @Override
    public boolean deleteMessage(MessageConfigurationDto message) {
        return false;
    }

    @Override
    public boolean addMessage(MessageConfigurationDto message) {
        return false;
    }

    @Override
    public List<InterfaceDecoderConfigurationDto> getInterfaceDecoders(String projectName) {
        return null;
    }

    @Override
    public List<InterfaceDecoderConfigurationDto> getInterfaceDecoders(String projectName, String platformName) {
        return null;
    }

    @Override
    public InterfaceDecoderConfigurationDto getInterfaceDecoder(String projectName, String platformName, String configurationName) {
        return null;
    }

    @Override
    public InterfaceDecoderConfigurationDto getInterfaceDecoder(UUID specificId) {
        return null;
    }

    @Override
    public boolean editInterfaceDecoder(InterfaceDecoderConfigurationDto interfaceDecoder) {
        return false;
    }

    @Override
    public boolean deleteInterfaceDecoder(InterfaceDecoderConfigurationDto interfaceDecoder) {
        return false;
    }

    @Override
    public boolean addInterfaceDecoder(InterfaceDecoderConfigurationDto interfaceDecoder) {
        return false;
    }

    @Override
    public List<TransportConfigurationDto> getTransportConfigurations(String projectName) {
        return null;
    }

    @Override
    public List<TransportConfigurationDto> getTransportConfigurations(String projectName, String platformName) {
        return null;
    }

    @Override
    public TransportConfigurationDto getTransportConfiguration(String projectName, String platformName, String configurationName) {
        return null;
    }

    @Override
    public TransportConfigurationDto getTransportConfiguration(UUID specificId) {
        return null;
    }

    @Override
    public boolean editTransportConfiguration(TransportConfigurationDto interfaceDecoder) {
        return false;
    }

    @Override
    public boolean deleteTransportConfiguration(TransportConfigurationDto interfaceDecoder) {
        return false;
    }

    @Override
    public boolean addTransportConfiguration(TransportConfigurationDto interfaceDecoder) {
        return false;
    }

    @Override
    public void loadConfiguration() {

        try {
            setUpDecoders();
        }
        catch(Exception e)
        {
            System.out.println("FUUUUUUUUCK");
            e.printStackTrace();
        }

        TransportServiceDto clientDto = new TransportServiceDto();
        clientDto.setTransportName("Client Test 1");
        clientDto.setTransportAsJson(SaveLoadUtils.Instance.getTransportServiceAsJson(myClientServiceImpl));


        TransportServiceDto serverDto = new TransportServiceDto();
        serverDto.setTransportName("Server Test 1");
        serverDto.setTransportAsJson(SaveLoadUtils.Instance.getTransportServiceAsJson(myServerServiceImpl));

        UUID clientId = myComSimClient.addTransportService(clientDto);
        UUID serverId = myComSimClient.addTransportService(serverDto);

        if( clientId != null && serverId != null)
            System.out.println("GREAT SUCESSSUUU");

        boolean setup = myComSimClient.setupComService();
        boolean started = myComSimClient.startComSimService();
        if( setup && started )
        {
            SendMessageDto msg1Dto = new SendMessageDto();
            msg1Dto.setMsgAsBytes(new BigInteger(message1.getMessageAsSerialString().toString(), 2).toByteArray());
            msg1Dto.setTransportServiceUUID( clientId );

            try {
                int count = 0;
                while (count < 100) {
                    myComSimClient.sendMessage(msg1Dto);
                }
            }
            catch(Exception e){
                System.out.println("Failed here1");
                e.printStackTrace();
            }
        }

    }

    /**
     * TODO REMOVE BELOW THIS JUST A FAST ADD IN
     */

    TCPClientTransportationService myClientServiceImpl = new TCPClientTransportationService();
    TCPServerTransportationService myServerServiceImpl = new TCPServerTransportationService();

    StaticIdDecoder clientDecoder = new StaticIdDecoder();
    StaticIdDecoder serverDecoder = new StaticIdDecoder();

    StandardMessage message1;
    StandardMessage message2;
    StandardMessage message3;
    private void setUpDecoders() throws Exception{
        clientDecoder.addIdPeekInfo(0, 5);
        clientDecoder.addIdPeekInfo(5, 3);

        serverDecoder.addIdPeekInfo(0, 5);
        serverDecoder.addIdPeekInfo(5, 3);

        // Message1
        message1 = new StandardMessage();
        StandardDataWord dataWord1 = new StandardDataWord();
        dataWord1.setNumberOfBytes(1);
        InterfaceDataField id1 = new StaticDataField();
        id1.setBitLength(5);
        id1.setBitOffset(0);
        id1.setName("Id 1");

        InterfaceDataField id2 = new StaticDataField();
        id2.setBitLength(3);
        id2.setBitOffset(5);
        id2.setName("Id 2");

        dataWord1.addDataField(id1);
        dataWord1.addDataField(id2);
        id1.setDataValue(new BigInteger("1"));
        id2.setDataValue(new BigInteger("1"));
        dataWord1.updateChangedFields();

        StandardDataWord dataWord2 = new StandardDataWord();
        dataWord2.setNumberOfBytes(2);

        InterfaceDataField field1 = new IntegerDataField();
        field1.setBitLength(7);
        field1.setBitOffset(0);
        field1.setName("Field1");

        InterfaceDataField field2 = new IntegerDataField();
        field2.setBitLength(1);
        field2.setBitOffset(8);
        field2.setName("Field2");

        InterfaceDataField field3 = new IntegerDataField();
        field3.setBitLength(7);
        field3.setBitOffset(9);
        field3.setName("Field3");

        dataWord2.addDataField(field1);
        dataWord2.addDataField(field2);
        dataWord2.addDataField(field3);
        field1.setDataValue(new BigInteger("1"));
        field2.setDataValue(new BigInteger("1"));
        field3.setDataValue(new BigInteger("1"));
        dataWord2.updateChangedFields();

        message1.addDataWord(dataWord1);
        message1.addDataWord(dataWord2);
        message1.setMessageName("Message1");
        message1.initializeMessageBinaryString();

        // Message2
        message2 = new StandardMessage();
        dataWord1 = new StandardDataWord();
        dataWord1.setNumberOfBytes(1);
        id1 = new StaticDataField();
        id1.setBitLength(5);
        id1.setBitOffset(0);
        id1.setName("Id 1_2");

        id2 = new StaticDataField();
        id2.setBitLength(3);
        id2.setBitOffset(5);
        id2.setName("Id 2_2");

        dataWord1.addDataField(id1);
        dataWord1.addDataField(id2);
        id1.setDataValue(new BigInteger("2"));
        id2.setDataValue(new BigInteger("2"));
        dataWord1.updateChangedFields();

        dataWord2 = new StandardDataWord();
        dataWord2.setNumberOfBytes(2);

        field1 = new StaticDataField();
        field1.setBitLength(7);
        field1.setBitOffset(0);
        field1.setName("Field1_2");

        field2 = new StaticDataField();
        field2.setBitLength(1);
        field2.setBitOffset(8);
        field2.setName("Field2_2");

        field3 = new StaticDataField();
        field3.setBitLength(7);
        field3.setBitOffset(9);
        field3.setName("Field3_2");

        dataWord2.addDataField(field1);
        dataWord2.addDataField(field2);
        dataWord2.addDataField(field3);
        field1.setDataValue(new BigInteger("2"));
        field2.setDataValue(new BigInteger("0"));
        field3.setDataValue(new BigInteger("2"));
        dataWord2.updateChangedFields();

        message2.addDataWord(dataWord1);
        message2.addDataWord(dataWord2);
        message2.setMessageName("Message2");
        message2.initializeMessageBinaryString();

        // Message3
        message3 = new StandardMessage();
        dataWord1 = new StandardDataWord();
        dataWord1.setNumberOfBytes(1);
        id1 = new StaticDataField();
        id1.setBitLength(5);
        id1.setBitOffset(0);
        id1.setName("Id 1_3");

        id2 = new StaticDataField();
        id2.setBitLength(3);
        id2.setBitOffset(5);
        id2.setName("Id 2_3");

        dataWord1.addDataField(id1);
        dataWord1.addDataField(id2);
        id1.setDataValue(new BigInteger("3"));
        id2.setDataValue(new BigInteger("3"));
        dataWord1.updateChangedFields();

        dataWord2 = new StandardDataWord();
        dataWord2.setNumberOfBytes(2);

        field1 = new StaticDataField();
        field1.setBitLength(7);
        field1.setBitOffset(0);
        field1.setName("Field1_3");

        field2 = new StaticDataField();
        field2.setBitLength(1);
        field2.setBitOffset(8);
        field2.setName("Field2_3");

        field3 = new StaticDataField();
        field3.setBitLength(7);
        field3.setBitOffset(9);
        field3.setName("Field3_3");

        dataWord2.addDataField(field1);
        dataWord2.addDataField(field2);
        dataWord2.addDataField(field3);
        field1.setDataValue(new BigInteger("3"));
        field2.setDataValue(new BigInteger("0"));
        field3.setDataValue(new BigInteger("3"));
        dataWord2.updateChangedFields();

        message3.addDataWord(dataWord1);
        message3.addDataWord(dataWord2);
        message3.setMessageName("Message3");
        message3.initializeMessageBinaryString();

        List<BigInteger> messageOneList = new ArrayList();
        List<BigInteger> messageTwoList = new ArrayList();
        List<BigInteger> messageThreeList = new ArrayList();

        messageOneList.add(new BigInteger("1"));
        messageOneList.add(new BigInteger("1"));

        messageTwoList.add(new BigInteger("2"));
        messageTwoList.add(new BigInteger("2"));

        messageThreeList.add(new BigInteger("3"));
        messageThreeList.add(new BigInteger("3"));

        clientDecoder.addMessage(messageOneList, message1);
        clientDecoder.addMessage(messageTwoList, message2);
        clientDecoder.addMessage(messageThreeList, message3);
        serverDecoder.addMessage(messageOneList, message1);
        serverDecoder.addMessage(messageTwoList, message2);
        serverDecoder.addMessage(messageThreeList, message3);

        myClientServiceImpl.setReceivePort(9003);
        myClientServiceImpl.setSendPort(9005);
        myClientServiceImpl.setSendHostAddress("127.0.0.1");
        myClientServiceImpl.setDecoder(clientDecoder);

        myServerServiceImpl.setReceivePort(9005);
        myServerServiceImpl.setWaitTimeoutSeconds(30);
        myServerServiceImpl.setDecoder(serverDecoder);
    }

}
