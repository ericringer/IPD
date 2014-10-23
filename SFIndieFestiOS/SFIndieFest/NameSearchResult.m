//
//  NameSearchResult.m
//  SFIndieFest
//
//  Created by eric ringer on 10/6/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import "NameSearchResult.h"

@implementation NameSearchResult
@synthesize data,dataMessage,status;
- (id)init
{
    self = [super init];
    if (self) {
        data = [[Data alloc] init];
    }
    return self;
}

//Init object with NSData
- (id)initWithResponse:(NSData *)response
{
    self = [super init];
    if (self) {
        [self assignValues:response];
    }
    return self;
}

//Parse name search response and populate data,dataMessage, and status objects
-(void)assignValues:(NSData *)response{
    NSDictionary* json = [NSJSONSerialization
                          JSONObjectWithData:response
                          
                          options:NSJSONReadingMutableLeaves
                          error:nil];
    NSLog(@"Search Result: %@",json);
    status = [json objectForKey:@"status"];
    if([status isEqualToString:@"success"]){
        data = [[Data alloc] initWithJSON:[json objectForKey:@"data"]];
    }else{
        data = [[Data alloc] init];
        dataMessage = [json objectForKey:@"data"];
    }
    
}
@end
