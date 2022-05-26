package com.talkon.talkon.controllers.chat;

import com.talkon.talkon.controllers.AbstractController;
import com.talkon.talkon.dtos.chat.ChatCreateDto;
import com.talkon.talkon.dtos.message.MessageIdsDto;
import com.talkon.talkon.dtos.message.MessageUpdateDto;
import com.talkon.talkon.services.chat.ChatService;
import com.talkon.talkon.services.message.MessageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController extends AbstractController<ChatService> {

    MessageService messageService;


    protected ChatController(ChatService service, MessageService messageService) {
        super(service);
        this.messageService = messageService;
    }

    /**
     * create chat for conversation mentor and mentee
     * @param chatCreateDto:  from id
     * @return saved chat id
     *  url: api/v1/chat
     */
    @PostMapping(PATH+"/chat")
    public HttpEntity<?> saveChatRoom(@RequestBody ChatCreateDto chatCreateDto){
        String currentUserId = "c45ab69a-057f-43ae-95b4-e8e7300c4e84";
        chatCreateDto.setCurrentUserId(currentUserId);
        return ResponseEntity.ok(service.create(chatCreateDto));
    }

    /**
     * Get all the contacts of the current user
     * @param authenticated user
     * @return list chat projection
     */
    @GetMapping(PATH+"/chat/contacts")
    public HttpEntity<?> getAllContacts(){
        String currentId = "c45ab69a-057f-43ae-95b4-e8e7300c4e84";
        return service.getAllContacts(currentId);
    }

    /**
     *
     * @param size the maximum number of elements on a page
     * @param page page value
     * @param chatId - for get messages
     * @return - all message pageable
     */
    @GetMapping(PATH+"/chat/{chatId}/messages")
    public HttpEntity<?> getAllMessages(
            @RequestParam(required = false, defaultValue = "40") int size,
            @RequestParam(required = false, defaultValue = "1") int page,
            @PathVariable String chatId
    ){
        String currentId = "c45ab69a-057f-43ae-95b4-e8e7300c4e84";
        return messageService.getAllMessages(chatId, size, page-1);
    }

    /**
     * when the message is read
     * @param messageId - which message
     * @return success or error
     */
    @PostMapping(PATH+"/chat/message/read")
    public HttpEntity<?> makeMessageAsRead(@RequestParam String messageId){
        return messageService.makeMessageAsRead(messageId);
    }

    @PostMapping(PATH + "/chat/messages/read")
    public HttpEntity<?> makeMessageAsRead(@RequestBody MessageIdsDto messageIds) {
        return messageService.makeMessagesAsRead(messageIds);
    }

    @PutMapping(PATH+"/chat/message")
    public HttpEntity<?> editMessage(@RequestBody MessageUpdateDto messageUpdateDto){
        messageService.update(messageUpdateDto);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping(PATH+"/chat/message/{id}")
    public HttpEntity<?> deleteMessage(@PathVariable String id){
        messageService.delete(id);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping(PATH+"/chat/{id}")
    public HttpEntity<?> deleteChat(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.ok("success");
    }







    






}
