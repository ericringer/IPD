//
//  Data.h
//  SFIndieFest
//
//  Created by eric ringer on 10/6/14.
//  Copyright (c) 2014 eric ringer. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "KnownFor.h"
@interface Data : NSObject

@property (nonatomic, retain)NSString *bio;
@property (nonatomic, retain)NSString *birthDate;
@property (nonatomic, retain)NSString *imdbEndPoint;
@property (nonatomic, retain)NSString *imdbUrl;
@property (nonatomic, retain)NSArray *knownFor;
@property (nonatomic, retain)NSString *name;
@property (nonatomic, retain)NSString *photo;
-(id)init;
- (id)initWithJSON:(NSDictionary *)json;
@end
